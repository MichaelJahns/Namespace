package michaelj.namespace.namespace.inventory.alchemy;

import javax.persistence.*;

@Entity
public class Potion {

    @GeneratedValue
    @Id
    private long id;

    @ManyToOne
    private PotionSatchel potionSatchel;
}
