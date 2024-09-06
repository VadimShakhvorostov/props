package props.service;

import org.springframework.stereotype.Component;
import props.model.Form;

import java.util.List;
import java.util.Map;

@Component
public interface FormService {
    List<Form> getAllForms();

    Form addNewForms(Form form);

    void addForms(Map<Integer, Integer> forms);

    void subtractForms(Map<Integer, Integer> forms);


}
