package props.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DIPLOMAS")
public class Diploma {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "NAME_DIPLOMA")
    private String name;
    @Column(name = "TYPE_DIPLOMA")
    private String type;
    @Column(name = "QUANTITY")
    private int quantity;
}
