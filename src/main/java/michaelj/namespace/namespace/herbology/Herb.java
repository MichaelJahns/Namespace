package michaelj.namespace.namespace.herbology;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static michaelj.namespace.namespace.board.Dice.rollDice;

@Entity
public class Herb{

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private HerbBag herbPouch;

    private String herbName;
    private int quantity;

    public Herb(){
        int quantityFound = rollDice(6);
        int lootRoll = rollDice(12);
        this.herbName = fieldHerbTable(lootRoll);
        this.quantity = quantityFound;
    }

    public Herb(String herbName, int quantity){
        this.herbName = herbName;
        this.quantity = quantity;
    }

    public Herb(String herbName, int quantity, HerbBag parent){
        this.herbName = herbName;
        this.quantity = quantity;
        this.herbPouch = parent;
    }

    private static String fieldHerbTable(int roll){
        String herb = "";
        switch (roll) {
            case 1:
                herb = "Ranarr Weed";
                break;
            case 2:
                herb = "Tarromin";
                break;
            case 3:
                herb = "Guarm Leaf";
                break;
            case 4:
                herb = "Harrlander";
                break;
            case 5:
                herb = "Avantoe";
                break;
            case 6:
                herb = "Kwuarm";
                break;
            case 7:
                herb = "Irit Leaf";
                break;
            case 8:
                herb = "Snapedragon";
                break;
            case 9:
                herb = "Lantadyme";
                break;
            case 10:
                herb = "Cadantine";
                break;
            case 11:
                herb = "ToadFlax";
                break;
            case 12:
                herb = "Dwarf Weed";
                break;
        }
        return herb;
    }


    //Getters
    public String getHerbName() {
        return herbName;
    }
    public int getQuantity() {
        return quantity;
    }
    //Setters
    public void setHerbName(String herbName) {
        this.herbName = herbName;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void incrementQuantity(int quantity){
        this.quantity += quantity;
    }
    public void decrementQuantity(int quantity) { this.quantity -= quantity; }

    public HerbBag getHerbPouch() {
        return herbPouch;
    }

    public void setHerbPouch(HerbBag herbPouch) {
        this.herbPouch = herbPouch;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
