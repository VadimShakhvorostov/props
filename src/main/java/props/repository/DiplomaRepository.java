package props.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import props.dto.diploma.Diploma;

public interface DiplomaRepository extends JpaRepository<Diploma, Integer> {

}
