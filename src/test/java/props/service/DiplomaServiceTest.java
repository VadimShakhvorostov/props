package props.service;


import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import props.entity.Diploma;
import props.repository.DiplomaRepository;
import props.service.imp.DiplomaServiceImp;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class DiplomaServiceTest {

    @InjectMocks
    DiplomaServiceImp diplomaService;

    @Mock
    DiplomaRepository diplomaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Должен добавлять дипломы к существующим")
    public void addDiplomas() {

        Map<Integer, Integer> diplomas = new HashMap<>();
        diplomas.put(1, 10);
        diplomas.put(2, 10);

        Diploma diploma1 = new Diploma();
        diploma1.setId(1);
        diploma1.setQuantity(10);

        Diploma diploma2 = new Diploma();
        diploma2.setId(2);
        diploma2.setQuantity(5);

        when(diplomaRepository.findAllById(diplomas.keySet())).thenReturn(Arrays.asList(diploma1, diploma2));
        when(diplomaRepository.saveAll(any())).thenReturn(Arrays.asList(diploma1, diploma2));
        when(diplomaRepository.existsById(any())).thenReturn(true);

        List<Diploma> result = diplomaService.addDiplomas(diplomas);

        assertEquals(2, diplomaRepository.findAllById(diplomas.keySet()).size());
        assertEquals(20, result.get(0).getQuantity());
        assertEquals(15, result.get(1).getQuantity());
    }

    @Test
    @DisplayName("Должен вычитать дипломы от существующих")
    public void subtractDiplomasTest() {

        Map<Integer, Integer> diplomas = new HashMap<>();
        diplomas.put(1, 5);
        diplomas.put(2, 8);

        Diploma diploma1 = new Diploma();
        diploma1.setId(1);
        diploma1.setQuantity(10);

        Diploma diploma2 = new Diploma();
        diploma2.setId(2);
        diploma2.setQuantity(10);

        when(diplomaRepository.findAllById(diplomas.keySet())).thenReturn(Arrays.asList(diploma1, diploma2));
        when(diplomaRepository.saveAll(any())).thenReturn(Arrays.asList(diploma1, diploma2));
        when(diplomaRepository.existsById(any())).thenReturn(true);

        List<Diploma> result = diplomaService.subtractDiplomas(diplomas);

        assertEquals(2, diplomaRepository.findAllById(diplomas.keySet()).size());
        assertEquals(5, result.get(0).getQuantity());
        assertEquals(2, result.get(1).getQuantity());
    }

    @Test
    @DisplayName("Должен обновлять дипломы")
    public void updateDiplomasTest() {
        Map<Integer, Integer> diplomas = new HashMap<>();
        diplomas.put(1, 5);
        diplomas.put(2, 8);

        Diploma diploma1 = new Diploma();
        diploma1.setId(1);
        diploma1.setQuantity(10);

        Diploma diploma2 = new Diploma();
        diploma2.setId(2);
        diploma2.setQuantity(10);

        when(diplomaRepository.findAllById(diplomas.keySet())).thenReturn(Arrays.asList(diploma1, diploma2));
        when(diplomaRepository.saveAll(any())).thenReturn(Arrays.asList(diploma1, diploma2));
        when(diplomaRepository.existsById(any())).thenReturn(true);

        List<Diploma> result = diplomaService.updateDiploma(diplomas);

        assertEquals(2, diplomaRepository.findAllById(diplomas.keySet()).size());
        assertEquals(5, result.get(0).getQuantity());
        assertEquals(8, result.get(1).getQuantity());
    }

    @Test
    public void synch() {

        Map<Integer, Integer> diplomas = new HashMap<>();
        diplomas.put(1, 5);
        diplomas.put(2, 8);

        Diploma diploma1 = new Diploma();
        diploma1.setId(1);
        diploma1.setQuantity(10);

        Diploma diploma2 = new Diploma();
        diploma2.setId(2);
        diploma2.setQuantity(10);


    }

}



