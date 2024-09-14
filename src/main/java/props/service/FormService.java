package props.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import props.model.Form;

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
