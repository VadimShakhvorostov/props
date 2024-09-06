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
@RequestMapping("/form")
@Validated
public class FormController {

    FormService service;

    @GetMapping
    public List<Form> getAllForm() {
        return service.getAllForms();
    }

    @PostMapping
    public Form addNewForm(@RequestBody Form form) {
        return service.addNewForms(form);
    }

    @PutMapping
    public void addForm(@RequestBody Map<Integer, Integer> forms) {
        service.addForms(forms);
    }

    @PutMapping("/subtract")
    public void subtractForm(@RequestBody Map<Integer, Integer> forms) {
        service.subtractForms(forms);
    }
}
