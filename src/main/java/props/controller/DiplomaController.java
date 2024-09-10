package props.controller;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import props.model.Diploma;
import props.service.DiplomaService;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/diplomas")
@Validated
public class DiplomaController {

    DiplomaService diplomaService;

    @GetMapping
    public List<Diploma> getAll() {
        return diplomaService.getAll();
    }

    @PostMapping("/add")
    public List<Diploma> addDiplomas(@RequestBody Map<Integer, Integer> diplomas) {
        return diplomaService.addDiplomas(diplomas);
    }

    @PostMapping("/subtract")
    public List<Diploma> subtractDiplomas(@RequestBody Map<Integer, Integer> diplomas) {
        return diplomaService.subtractDiplomas(diplomas);
    }
}
