package props.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import props.dto.forms.FormEntity;
import props.service.FormService;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:8080")
@AllArgsConstructor
@RestController
@RequestMapping("/forms")
@Validated
public class FormController {

    FormService formService;

    @GetMapping
    public List<FormEntity> getAllForm() {
        return formService.getAllForms();
    }

    @PostMapping("/new")
    public FormEntity addNewForm(@RequestBody FormEntity formEntity) {
        return formService.addNewForms(formEntity);
    }

    @PostMapping("/add")
    public List<FormEntity> addForm(@RequestBody Map<Integer, Integer> forms) {
        return formService.addForms(forms);
    }

    @PostMapping("/subtract")
    public List<FormEntity> subtractForm(@RequestBody Map<Integer, Integer> forms) {
        return formService.subtractForms(forms);
    }

    @PostMapping("/update")
    public List<FormEntity> updateForm(@RequestBody Map<Integer, Integer> forms) {
        return formService.updateForm(forms);
    }
}
