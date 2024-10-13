package props.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import props.entity.Composition;

@Repository
public interface CompositionRepository extends JpaRepository<Composition, Integer> {
}
