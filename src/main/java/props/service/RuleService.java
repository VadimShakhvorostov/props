package props.service;


import org.springframework.stereotype.Component;
import props.entity.Rule;

import java.util.List;

@Component
public interface RuleService {

    List<Rule> getAll();
}
