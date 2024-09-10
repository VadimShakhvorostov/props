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

    @Column(name = "name_game")
    private String name;

    @Column(name = "formsId")
    private List<Integer> formsId;

    @Column(name = "diplomasId")
    private List<Integer> diplomasId;



}
