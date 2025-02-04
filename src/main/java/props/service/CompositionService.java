package props.service;

import org.springframework.stereotype.Component;
import props.dto.composition.Composition;

import java.util.List;

@Component
public interface CompositionService {

    List<Composition> getAll();
}
