package props.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "NAME_GAME")
    private String name;

    @Column(name = "DIPLOMAS_ID")
    private List<Integer> diplomasId;

    @Column(name = "COMPOSITION_ID")
    private int compositionId;

}
