package props.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import props.dto.forms.FormEntity;

@Repository
public interface FormRepository extends JpaRepository<FormEntity, Integer> {

    boolean existsByName(String name);
}

