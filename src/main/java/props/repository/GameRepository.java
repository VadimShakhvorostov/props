package props.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import props.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    boolean existsByName(String name);
}
