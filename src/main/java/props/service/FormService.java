package props.service;

import org.springframework.stereotype.Component;
import props.entity.Form;

import java.util.List;
import java.util.Map;

@Component
public interface FormService {
    List<Form> getAllForms();

    Form addNewForms(Form form);

    List<Form> addForms(Map<Integer, Integer> forms);

    List<Form> subtractForms(Map<Integer, Integer> forms);
    List<Form> updateForm(Map<Integer, Integer> forms);


}
