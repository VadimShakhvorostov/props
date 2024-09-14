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
@Table(name = "RULES")
public class Rule {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "ENTITY_ID")
    private int entityId;

    @Column(name = "VALUED_USE")
    private int valueToUse;
}
