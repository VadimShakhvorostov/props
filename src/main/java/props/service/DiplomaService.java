package props.service;


import org.springframework.stereotype.Component;
import props.model.Diploma;

import java.util.List;

@Component
public interface DiplomaService {
    List<Diploma> getAll();


}
