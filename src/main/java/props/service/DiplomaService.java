package props.service;


import org.springframework.stereotype.Component;
import props.model.Diploma;

import java.util.List;
import java.util.Map;

@Component
public interface DiplomaService {
    List<Diploma> getAll();

    List<Diploma> addDiplomas(Map<Integer, Integer> diplomas);

    List<Diploma> subtractDiplomas(Map<Integer, Integer> diplomas);
}
