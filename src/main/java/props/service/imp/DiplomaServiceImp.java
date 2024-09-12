package props.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import props.exception.NotFoundException;
import props.model.Diploma;
import props.repository.DiplomaRepository;
import props.service.DiplomaService;

import java.util.ArrayList;
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
    public List<Diploma> addDiplomas(Map<Integer, Integer> diplomas) {
        validationId(diplomas);
        List<Diploma> diplomaToSave = new ArrayList<>();
        List<Diploma> diplomasDb = diplomaRepository.findAllById(diplomas.keySet());
        for (Diploma diploma : diplomasDb) {
            int quantityInDb = diploma.getQuantity();
            int quantityToAdditional = diplomas.get(diploma.getId());
            diploma.setQuantity(quantityInDb + quantityToAdditional);
            diplomaToSave.add(diploma);
        }
        return diplomaRepository.saveAll(diplomaToSave);
    }

    @Override
    public List<Diploma> subtractDiplomas(Map<Integer, Integer> diplomas) {
        validationId(diplomas);
        List<Diploma> diplomaToSave = new ArrayList<>();
        List<Diploma> diplomasDb = diplomaRepository.findAllById(diplomas.keySet());
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
