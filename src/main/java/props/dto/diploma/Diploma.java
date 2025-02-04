package props.dto.diploma;

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
    @Column(name = "POSITION")
    private int position;
    @Column(name = "QUANTITY")
    private int quantity;
}
