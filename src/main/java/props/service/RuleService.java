package props.service;


import org.springframework.stereotype.Component;
import props.model.Rule;

import java.util.List;

@Component
public interface RuleService {

    List<Rule> getAll();
}
