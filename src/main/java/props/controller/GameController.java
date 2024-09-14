package props.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import props.model.Game;
import props.model.GameIn;
import props.service.imp.GameServiceImp;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/games")
public class GameController {

    final GameServiceImp gameService;

    @GetMapping
    public List<Game> getAllGame() {
        return gameService.getAllGame();
    }

    @PostMapping("/add")
    public Game addGame(@RequestBody GameIn gameIn) {
        return gameService.addGame(gameIn);
    }

    @PostMapping("/event")
    public void event(@RequestBody Map<Integer, Integer> map) {
        gameService.event(map);
    }
}
