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
@Table(name = "COMPOSITIONS")
public class Composition {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "RULES_ID")
    private List<Integer> rulesId;

}
