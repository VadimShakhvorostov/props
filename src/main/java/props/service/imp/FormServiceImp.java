package props.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import props.exception.NotFoundException;
import props.exception.ValidationException;
import props.model.Form;
import props.repository.FormRepository;
import props.service.FormService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FormServiceImp implements FormService {

    FormRepository formRepository;

    @Override
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @Override
    public Form addNewForms(Form form) {
        validationName(form);
        return formRepository.save(form);
    }

    @Override
    public List<Form> addForms(Map<Integer, Integer> forms) {
        validationId(forms);
        List<Form> formToSave = new ArrayList<>();
        List<Form> formsDb = formRepository.findAllById(forms.keySet());
        for (Form form : formsDb) {
            int quantityInDb = form.getQuantity();
            int quantityToAdditional = forms.get(form.getId());
            form.setQuantity(quantityInDb + quantityToAdditional);
            formToSave.add(form);
        }
        return formRepository.saveAll(formToSave);
    }

    @Override
    public List<Form> subtractForms(Map<Integer, Integer> forms) {
        validationId(forms);
        List<Form> formToSave = new ArrayList<>();
        List<Form> formsDb = formRepository.findAllById(forms.keySet());
        for (Form form : formsDb) {
            int quantityInDb = form.getQuantity();
            int quantityToSubtraction = forms.get(form.getId());
            int result = quantityInDb - quantityToSubtraction;
            if (result < 0) {
                result = 0;
            }
            form.setQuantity(result);
            formToSave.add(form);
            formRepository.save(form);
        }
        return formRepository.saveAll(formToSave);
    }

    private void validationId(Map<Integer, Integer> forms) {
        for (Integer id : forms.keySet()) {
            if (!formRepository.existsById(id)) {
                throw new NotFoundException("Бланка с id: " + id + " не существует");
            }
        }
    }

    private void validationName(Form form) {
        if (formRepository.existsByName(form.getName())) {
            throw new ValidationException("Бланк с именем:" + form.getName() + " уже существует");
        }
    }

    @Override
    public List<Form> updateForm(Map<Integer, Integer> forms) {
        validationId(forms);
        List<Form> formToSave = new ArrayList<>();
        List<Form> formsDb = formRepository.findAllById(forms.keySet());
        for (Form form : formsDb) {
            int quantityToUpdate = forms.get(form.getId());
            form.setQuantity(quantityToUpdate);
            formToSave.add(form);
        }
        return formRepository.saveAll(formToSave);
    }
}