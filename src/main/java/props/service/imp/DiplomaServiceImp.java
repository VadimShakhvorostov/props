package props.service.imp;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import props.exception.NotFoundException;
import props.entity.Diploma;
import props.repository.DiplomaRepository;
import props.service.DiplomaService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DiplomaServiceImp implements DiplomaService {

    DiplomaRepository diplomaRepository;

    @Override
    public List<Diploma> getAll() {
        return diplomaRepository.findAll();
    }

    @Override
    @Transactional
    public List<Diploma> addDiplomas(Map<Integer, Integer> diplomas) {
        validationId(diplomas);

        int count = 0;
        while (count < 3) {
            List<Diploma> diplomasDb = diplomaRepository.findAllById(diplomas.keySet());
            try {
                return diplomaRepository.saveAll(getDiplomaToSaveWithAdditional(diplomasDb, diplomas));
            } catch (OptimisticLockException ex) {
                count++;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("Не удалось обновить данные");
    }

    private Collection<Diploma> getDiplomaToSaveWithAdditional(Collection<Diploma> diplomasDb, Map<Integer, Integer> diplomas) {
        Collection<Diploma> diplomaToSave = new ArrayList<>();
        for (Diploma diploma : diplomasDb) {
            int quantityInDb = diploma.getQuantity();
            int quantityToAdditional = diplomas.get(diploma.getId());
            diploma.setQuantity(quantityInDb + quantityToAdditional);
            diplomaToSave.add(diploma);
        }
        return diplomaToSave;
    }

    @Override
    public List<Diploma> subtractDiplomas(Map<Integer, Integer> diplomas) {
        validationId(diplomas);
        int count = 0;
        while (count < 3) {
            List<Diploma> diplomasDb = diplomaRepository.findAllById(diplomas.keySet());
            try {
                return diplomaRepository.saveAll(getDiplomaToSaveWithSubtraction(diplomasDb, diplomas));
            } catch (OptimisticLockException ex) {
                count++;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("Не удалось обновить данные");
    }

    private Collection<Diploma> getDiplomaToSaveWithSubtraction(Collection<Diploma> diplomasDb, Map<Integer, Integer> diplomas) {
        Collection<Diploma> diplomaToSave = new ArrayList<>();

        for (Diploma diploma : diplomasDb) {
            int quantityInDb = diploma.getQuantity();
            int quantityToSubtraction = diplomas.get(diploma.getId());
            int result = quantityInDb - quantityToSubtraction;
            if (result < 0) {
                result = 0;
            }
            diploma.setQuantity(result);
            diplomaToSave.add(diploma);
        }
        return diplomaToSave;
    }

    @Override
    public List<Diploma> updateDiploma(Map<Integer, Integer> diplomas) {
        validationId(diplomas);
        List<Diploma> diplomaToSave = new ArrayList<>();
        List<Diploma> diplomasDb = diplomaRepository.findAllById(diplomas.keySet());
        for (Diploma diploma : diplomasDb) {
            int quantityToUpdate = diplomas.get(diploma.getId());
            diploma.setQuantity(quantityToUpdate);
            diplomaToSave.add(diploma);
        }
        return diplomaRepository.saveAll(diplomaToSave);
    }



    private void validationId(Map<Integer, Integer> forms) {
        for (Integer id : forms.keySet()) {
            if (!diplomaRepository.existsById(id)) {
                throw new NotFoundException("Диплома с id: " + id + " не существует");
            }
        }
    }
}
