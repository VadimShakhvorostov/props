package props.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import props.exception.NotFoundException;
import props.exception.ValidationException;
import props.model.*;
import props.repository.*;
import props.service.GameService;

import java.util.*;

@Service
@AllArgsConstructor
public class GameServiceImp implements GameService {

    final GameRepository gameRepository;
    final DiplomaRepository diplomaRepository;
    final FormRepository formRepository;
    final RuleRepository ruleRepository;
    final CompositionRepository compositionRepository;
    final static int QUANTITY_DIPLOMA_TO_SUBTRACTION = 1;

    @Override
    public Game addGame(GameIn gameIn) {

        validationGameName(gameIn);
        validationFormId(gameIn);

        List<Integer> rulesId = saveRulesFromGame(gameIn);
        int compositionId = saveComposition(rulesId);

        List<Diploma> diplomas = saveDiplomasFromGame(gameIn);
        List<Integer> diplomasId = new ArrayList<>();

        for (Diploma diploma : diplomas) {
            diplomasId.add(diploma.getId());
        }

        Game game = new Game();
        game.setName(gameIn.getName());
        game.setDiplomasId(diplomasId);
        game.setCompositionId(compositionId);

        return gameRepository.save(game);
    }

    private List<Integer> saveRulesFromGame(GameIn gameIn) {

        List<Rule> rulesToBd = new ArrayList<>();
        Map<Integer, Integer> rules = gameIn.getRules();

        for (Integer id : rules.keySet()) {
            Rule rule = new Rule();
            rule.setEntityId(id);
            rule.setValueToUse(rules.get(id));
            rulesToBd.add(rule);
        }

        List<Rule> rulesToDb = ruleRepository.saveAll(rulesToBd);
        List<Integer> rulesId = new ArrayList<>();
        for (Rule rule : rulesToDb) {
            rulesId.add(rule.getId());
        }

        return rulesId;
    }

    private Integer saveComposition(List<Integer> rulesId) {
        Composition composition = new Composition();
        composition.setRulesId(rulesId);
        return compositionRepository.save(composition).getId();
    }

    private List<Diploma> saveDiplomasFromGame(GameIn gameIn) {
        List<Diploma> diplomaToBd = new ArrayList<>();
        for (Integer diplomaPosition : gameIn.getDiplomas().keySet()) {
            Diploma diploma = new Diploma();
            diploma.setPosition(diplomaPosition);
            diploma.setQuantity(gameIn.getDiplomas().get(diplomaPosition));
            diplomaToBd.add(diploma);
        }
        return diplomaRepository.saveAll(diplomaToBd);
    }

    @Override
    public void event(Map<Integer, Integer> map) {
        for (Integer id : map.keySet()) {

            Optional<Game> game = gameRepository.findById(id);
            if (game.isEmpty()) {
                throw new NotFoundException("Игры с id " + id + " не существует");
            }

            Optional<Composition> composition = compositionRepository.findById(game.get().getCompositionId());
            if (composition.isEmpty()) {
                throw new NotFoundException("Состава с id " + id + " не существует");
            }

            List<Rule> rules = ruleRepository.findAllById(composition.get().getRulesId());

            for (Rule rule : rules) {
                int formId = rule.getEntityId();

                int valueToSubtraction = rule.getValueToUse() * map.get(id);


                Optional<Form> form = formRepository.findById(formId);
                if (form.isEmpty()) {
                    throw new NotFoundException("Бланка с id: " + id + " не существует");
                }

                int quantityInDb = form.get().getQuantity();

                int result = quantityInDb - valueToSubtraction;
                if (result < 0) {
                    result = 0;
                }

                form.get().setQuantity(result);

                formRepository.save(form.get());
            }

            List<Diploma> diplomas = diplomaRepository.findAllById(game.get().getDiplomasId());
            for (Diploma diploma : diplomas) {
                int quantityInDb = diploma.getQuantity();
                diploma.setQuantity(quantityInDb - QUANTITY_DIPLOMA_TO_SUBTRACTION);
                diplomaRepository.save(diploma);
            }
        }
    }

    @Override
    public List<Game> getAllGame() {
        return gameRepository.findAll();
    }


    private void validationGameName(GameIn gameIn) {
        if (gameRepository.existsByName(gameIn.getName())) {
            throw new ValidationException("Игра с именем " + gameIn.getName() + " уже существует");
        }
    }

    private void validationFormId(GameIn gameIn) {
        Set<Integer> formId = gameIn.getRules().keySet();
        for (Integer id : formId) {
            if (!formRepository.existsById(id)) {
                throw new ValidationException("Бланка с id " + id + " не существует");
            }
        }
    }
}