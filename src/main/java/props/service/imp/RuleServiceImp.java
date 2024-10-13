package props.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import props.entity.Rule;
import props.repository.RuleRepository;
import props.service.RuleService;

import java.util.List;


@Service
@AllArgsConstructor
public class RuleServiceImp implements RuleService {

    final RuleRepository ruleRepository;

    @Override
    public List<Rule> getAll() {
        return ruleRepository.findAll();
    }
}
