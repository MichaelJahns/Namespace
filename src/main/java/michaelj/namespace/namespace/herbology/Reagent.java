package michaelj.namespace.namespace.herbology;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static michaelj.namespace.namespace.board.Dice.rollDice;

@Entity
public class Reagent{

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private HerbBag reagentPouch;

    private String reagentName;
    private int quantity;

    public Reagent(){
        int quantityFound = rollDice(6);
        int lootRoll = rollDice(8);
        this.reagentName = fieldReagentTable(lootRoll);
        this.quantity = quantityFound;
    }

    public Reagent(String reagentName, int quantity){
        this.reagentName = reagentName;
        this.quantity = quantity;
    }

    public Reagent(String reagentName, int quantity, HerbBag parent){
        this.reagentName = reagentName;
        this.quantity = quantity;
        this.reagentPouch = parent;
    }

    private static String fieldReagentTable(int roll){
        String reagent = "";
        switch (roll) {
            case 1:
                reagent = "Snape Grass";
                break;
            case 2:
                reagent = "Limwurt root";
                break;
            case 3:
                reagent = "Eye of newt";
                break;
            case 4:
                reagent = "Red Spider's Eyes";
                break;
            case 5:
                reagent = "Mort Myre Fungus";
                break;
            case 6:
                reagent = "Toad's Legs";
                break;
            case 7:
                reagent = "Potato Cactus";
                break;
            case 8:
                reagent = "Snail Slime";
                break;
        }
        return reagent;
    }

    public String getReagentName() {
        return reagentName;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setReagentName(String reagentName) {
        this.reagentName = reagentName;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void incrementQuantity(int quantity){
        this.quantity += quantity;
    }
    public void decrementQuantity(int quantity) { this.quantity -= quantity; }



    public HerbBag getReagentPouch() {
        return reagentPouch;
    }

    public void setReagentPouch(HerbBag reagentPouch) {
        this.reagentPouch = reagentPouch;
    }
}
