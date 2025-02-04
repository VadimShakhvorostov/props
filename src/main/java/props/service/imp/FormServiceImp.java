package props.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import props.dto.forms.FormEntity;
import props.exception.NotFoundException;
import props.exception.ValidationException;
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
    public List<FormEntity> getAllForms() {
        return formRepository.findAll();
    }

    @Override
    public FormEntity addNewForms(FormEntity formEntity) {
        validationName(formEntity);
        return formRepository.save(formEntity);
    }

    @Override
    public List<FormEntity> addForms(Map<Integer, Integer> forms) {
        validationId(forms);
        List<FormEntity> formEntityToSave = new ArrayList<>();
        List<FormEntity> formsDb = formRepository.findAllById(forms.keySet());
        for (FormEntity formEntity : formsDb) {
            int quantityInDb = formEntity.getQuantity();
            int quantityToAdditional = forms.get(formEntity.getId());
            formEntity.setQuantity(quantityInDb + quantityToAdditional);
            formEntityToSave.add(formEntity);
        }
        return formRepository.saveAll(formEntityToSave);
    }

    @Override
    public List<FormEntity> subtractForms(Map<Integer, Integer> forms) {
        validationId(forms);
        List<FormEntity> formEntityToSave = new ArrayList<>();
        List<FormEntity> formsDb = formRepository.findAllById(forms.keySet());
        for (FormEntity formEntity : formsDb) {
            int quantityInDb = formEntity.getQuantity();
            int quantityToSubtraction = forms.get(formEntity.getId());
            int result = quantityInDb - quantityToSubtraction;
            if (result < 0) {
                result = 0;
            }
            formEntity.setQuantity(result);
            formEntityToSave.add(formEntity);
            formRepository.save(formEntity);
        }
        return formRepository.saveAll(formEntityToSave);
    }

    private void validationId(Map<Integer, Integer> forms) {
        for (Integer id : forms.keySet()) {
            if (!formRepository.existsById(id)) {
                throw new NotFoundException("Бланка с id: " + id + " не существует");
            }
        }
    }

    private void validationName(FormEntity formEntity) {
        if (formRepository.existsByName(formEntity.getName())) {
            throw new ValidationException("Бланк с именем:" + formEntity.getName() + " уже существует");
        }
    }

    @Override
    public List<FormEntity> updateForm(Map<Integer, Integer> forms) {
        validationId(forms);
        List<FormEntity> formEntityToSave = new ArrayList<>();
        List<FormEntity> formsDb = formRepository.findAllById(forms.keySet());
        for (FormEntity formEntity : formsDb) {
            int quantityToUpdate = forms.get(formEntity.getId());
            formEntity.setQuantity(quantityToUpdate);
            formEntityToSave.add(formEntity);
        }
        return formRepository.saveAll(formEntityToSave);
    }
}