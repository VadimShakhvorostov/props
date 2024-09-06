package props.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import props.exception.ValidationException;
import props.model.Form;
import props.repository.FormRepository;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FormServiceImp implements FormService {

    FormRepository repository;

    @Override
    public List<Form> getAllForms() {
        return repository.findAll();
    }

    @Override
    public Form addNewForms(Form form) {
        validationName(form);
        return repository.save(form);
    }

    @Override
    public void addForms(Map<Integer, Integer> forms) {
        validationId(forms);
        List<Form> formsDb = repository.findAllById(forms.keySet());
        for (Form form : formsDb) {
            int quantityInDb = form.getQuantity();
            int quantityToAdditional = forms.get(form.getId());
            form.setQuantity(quantityInDb + quantityToAdditional);
            repository.save(form);
        }
    }

    @Override
    public void subtractForms(Map<Integer, Integer> forms) {
        validationId(forms);
        List<Form> formsDb = repository.findAllById(forms.keySet());
        for (Form form : formsDb) {
            int quantityInDb = form.getQuantity();
            int quantityToSubtraction = forms.get(form.getId());
            int result = quantityInDb - quantityToSubtraction;
            if (result < 0) {
                result = 0;
            }
            form.setQuantity(result);
            repository.save(form);
        }
    }

    private void validationId(Map<Integer, Integer> forms) {
        for (Integer id : forms.keySet()) {
            if (!repository.existsById(id)) {
                throw new ValidationException("Бланка с id -" + id + "- не существует");
            }
        }
    }

    private void validationName(Form form) {
        if (repository.existsByName(form.getName())) {
            throw new ValidationException("Бланка с именем -" + form.getName() + "- уже существует");
        }
    }
}