package props.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @Version
    @Setter(AccessLevel.NONE)
    private int version;

}
