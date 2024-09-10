package props.service;

import org.springframework.stereotype.Component;
import props.model.GameIn;
import props.model.GameOut;

import java.util.List;

@Component
public interface GameService {

    GameOut addGame(GameIn gameIn);

    List<GameOut> getAllGame();
}
