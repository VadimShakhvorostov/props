package props.service;

import org.springframework.stereotype.Component;
import props.model.Game;
import props.model.GameIn;

import java.util.List;
import java.util.Map;

@Component
public interface GameService {

    Game addGame(GameIn gameIn);
    List<Game> getAllGame();
    void event(Map<Integer, Integer> map);
}
