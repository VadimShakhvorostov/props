package props.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import props.model.Diploma;

import java.util.List;

public interface DiplomaRepository extends JpaRepository<Diploma, Integer> {

}
