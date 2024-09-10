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
@Table(name = "FORMS")
public class Form {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "NAME_FORMS")
    private String name;

    @Column(name = "QUANTITY")
    private int quantity;

    //compositionId

}
