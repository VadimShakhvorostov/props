package props.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import props.model.Form;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {

    boolean existsByName(String name);
}

