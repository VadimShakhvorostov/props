package props.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameIn {

    private int id;
    private String name;
    private Map<Integer, Integer> diplomas;
    private Map<Integer, Integer> rules;
}
