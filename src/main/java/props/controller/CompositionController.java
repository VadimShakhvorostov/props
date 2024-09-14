package props.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import props.model.Composition;
import props.service.CompositionService;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/composition")
public class CompositionController {

    final CompositionService compositionService;

    @GetMapping
    public List<Composition> getAll() {
        return compositionService.getAll();
    }

}
