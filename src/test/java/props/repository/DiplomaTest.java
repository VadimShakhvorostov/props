package props.repository;

import lombok.AllArgsConstructor;
import org.assertj.core.api.DoublePredicateAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import props.entity.Diploma;
import props.repository.DiplomaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;


@DataJpaTest
public class DiplomaTest {

    @Autowired
    private DiplomaRepository diplomaRepository;

//    @BeforeEach
//    public void insertDiploma(){
//        Diploma diploma = new Diploma();
//        diploma.setId(1);
//        diploma.setPosition(1);
//        diploma.setQuantity(3);
//        diplomaRepository.save(diploma);
//    }

    @Test
    public void test(){
        Collection<Diploma> diplomas = diplomaRepository.findAll();

        assertThat(3).isEqualTo(diplomas.size());
    }



}
