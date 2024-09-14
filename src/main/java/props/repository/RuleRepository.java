package props.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import props.model.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Integer> {
}
