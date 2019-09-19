package michaelj.namespace.namespace.herbology;

import javax.persistence.*;

@Entity
public class Ingredient {
    @GeneratedValue
    @Id
    private long id;

    @ManyToOne
    private HerbBag herbBag;

    private String name;
    private int quantity;
    private boolean isReagent;

    public Ingredient(){
        this.name = "Shatterback Blooms";
        this.quantity = 1;
        this.isReagent = false;
    }

    public Ingredient(String name, int quantity, boolean isReagent){
        this.name = name;
        this.quantity = quantity;
        this.isReagent = isReagent;
    }
}
