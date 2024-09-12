package props.service;

import org.springframework.stereotype.Component;
import props.model.Game;
import props.model.GameIn;

import java.util.List;

@Component
public interface GameService {

    Game addGame(GameIn gameIn);
    List<Game> getAllGame();
}
