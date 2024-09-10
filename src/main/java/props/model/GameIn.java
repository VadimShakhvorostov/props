package props.model;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameIn {

    private int id;
    private String name;
    private List<Integer> formsId;
    private Map<Integer, Integer> diplomas;

    //Composition composition
}
