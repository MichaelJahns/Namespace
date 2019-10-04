package michaelj.namespace.namespace.alchemy;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
public class Potion {

    @GeneratedValue
    @Id
    private long id;

    @ManyToOne
    private PotionSatchel potionSatchel;
}
