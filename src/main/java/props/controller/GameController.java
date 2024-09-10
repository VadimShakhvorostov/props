package props.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import props.model.GameIn;
import props.model.GameOut;
import props.service.imp.GameServiceImp;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/games")
@Validated
public class GameController {

    GameServiceImp gameService;

    @GetMapping
    public List<GameOut> getAllGame() {
        return gameService.getAllGame();
    }

    @PostMapping("/add")
    public GameOut addGame(@RequestBody GameIn gameIn) {
        return gameService.addGame(gameIn);
    }

}
