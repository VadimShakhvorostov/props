package props.service;

import org.springframework.stereotype.Component;
import props.dto.forms.FormEntity;

import java.util.List;
import java.util.Map;

@Component
public interface FormService {
    List<FormEntity> getAllForms();

    FormEntity addNewForms(FormEntity formEntity);

    List<FormEntity> addForms(Map<Integer, Integer> forms);

    List<FormEntity> subtractForms(Map<Integer, Integer> forms);
    List<FormEntity> updateForm(Map<Integer, Integer> forms);


}
