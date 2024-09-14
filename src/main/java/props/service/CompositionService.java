package props.service;

import org.springframework.stereotype.Component;
import props.model.Composition;

import java.util.List;

@Component
public interface CompositionService {

    List<Composition> getAll();
}
