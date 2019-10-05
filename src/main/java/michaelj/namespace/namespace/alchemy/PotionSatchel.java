package michaelj.namespace.namespace.alchemy;

import michaelj.namespace.namespace.herbology.Herb;
import michaelj.namespace.namespace.inventory.Inventory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PotionSatchel {
    @GeneratedValue
    @Id
    private long id;

    @OneToOne(mappedBy = "potionSatchel")
    private Inventory inventory;

    @OneToMany(mappedBy = "potionSatchel")
    private List<Potion> potions;

    public PotionSatchel(){
        this.potions = new ArrayList<>();
    }

    public List<Potion> getPotions() {
        return potions;
    }

    public void setPotions(List<Potion> potions) {
        this.potions = potions;
    }
}

