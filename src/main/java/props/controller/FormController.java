package props.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import props.model.Form;
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
    public List<Form> getAllForm() {
        return formService.getAllForms();
    }

    @PostMapping("/new")
    public Form addNewForm(@RequestBody Form form) {
        return formService.addNewForms(form);
    }

    @PostMapping("/add")
    public List<Form> addForm(@RequestBody Map<Integer, Integer> forms) {
        return formService.addForms(forms);
    }

    @PostMapping("/subtract")
    public List<Form> subtractForm(@RequestBody Map<Integer, Integer> forms) {
        return formService.subtractForms(forms);
    }

    @PostMapping("/update")
    public List<Form> updateForm(@RequestBody Map<Integer, Integer> forms) {
        return formService.updateForm(forms);
    }
}
