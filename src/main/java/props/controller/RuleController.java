package props.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import props.model.Rule;
import props.service.RuleService;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/rule")
public class RuleController {

    final RuleService ruleService;

    @GetMapping
    public List<Rule> getAll() {
        return ruleService.getAll();
    }
}
