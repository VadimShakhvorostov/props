package props.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import props.model.Composition;
import props.repository.CompositionRepository;
import props.service.CompositionService;

import java.util.List;

@Service
@AllArgsConstructor
public class CompositionServiceImp implements CompositionService {

    final CompositionRepository compositionRepository;

    @Override
    public List<Composition> getAll() {
        return compositionRepository.findAll();
    }
}
