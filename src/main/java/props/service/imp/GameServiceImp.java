package props.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import props.exception.ValidationException;
import props.model.Diploma;
import props.model.Game;
import props.model.GameIn;
import props.model.GameOut;
import props.repository.DiplomaRepository;
import props.repository.FormRepository;
import props.repository.GameRepository;
import props.service.GameService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GameServiceImp implements GameService {
    GameRepository gameRepository;
    DiplomaRepository diplomaRepository;
    FormRepository formRepository;


    @Override
    public GameOut addGame(GameIn gameIn) {

        validationGameId(gameIn);
        validationFormId(gameIn);


        List<Diploma> diplomaToBd = new ArrayList<>();
        for (Integer diplomaPosition : gameIn.getDiplomas().keySet()) {
            Diploma diploma = new Diploma();
            diploma.setPosition(diplomaPosition);
            diploma.setQuantity(gameIn.getDiplomas().get(diplomaPosition));
            diplomaToBd.add(diploma);
        }

        List<Diploma> diplomas = diplomaRepository.saveAll(diplomaToBd);
        List<Integer> diplomasId = new ArrayList<>();
        for (Diploma diploma : diplomas) {
            diplomasId.add(diploma.getId());
        }

        Game game = new Game();
        game.setName(gameIn.getName());
        game.setFormsId(gameIn.getFormsId());
        game.setDiplomasId(diplomasId);
        Game gameDb = gameRepository.save(game);

        GameOut gameOut = new GameOut();
        gameOut.setName(gameIn.getName());
        gameOut.setId(gameDb.getId());
        gameOut.setDiplomas(diplomas);
        gameOut.setFormsId(gameIn.getFormsId());
        return gameOut;
    }

    @Override
    public List<GameOut> getAllGame() {
        List<GameOut> gamesOut = new ArrayList<>();
        List<Game> games = gameRepository.findAll();
        for (Game game : games) {
            GameOut gameOut = new GameOut();
            gameOut.setId(game.getId());
            gameOut.setName(game.getName());
            gameOut.setFormsId(game.getFormsId());
            gameOut.setDiplomas(diplomaRepository.findAllById(game.getDiplomasId()));
            gamesOut.add(gameOut);
        }
        return gamesOut;
    }

    private void validationGameId(GameIn gameIn) {
        if (gameRepository.existsByName(gameIn.getName())) {
            throw new ValidationException("Игра с именем " + gameIn.getName() + " уже существует");
        }
    }

    private void validationFormId(GameIn gameIn) {
        List<Integer> formId = gameIn.getFormsId();
        for (Integer id : formId) {
            if (!formRepository.existsById(id)) {
                throw new ValidationException("Бланка с id " + id + " не существует");
            }
        }
    }
}