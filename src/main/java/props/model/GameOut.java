package props.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameOut {

    private int id;
    private String name;
    private List<Integer> formsId;
    private List<Diploma> diplomas;

    //этот класс не нужен. везде возвращаем Game
}
