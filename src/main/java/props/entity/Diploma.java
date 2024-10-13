package props.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "DIPLOMAS")
public class Diploma {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "POSITION")
    private int position;
    @Column(name = "QUANTITY")
    private int quantity;
    @Version
    @Setter(AccessLevel.NONE)
    int version;
}
