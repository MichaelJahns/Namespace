package michaelj.namespace.namespace.herbology;

import michaelj.namespace.namespace.inventory.Inventory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.*;

import static michaelj.namespace.namespace.board.Dice.rollDice;


@Entity
public class HerbBag {
    @GeneratedValue
    @Id
    private long id;

    @OneToOne(mappedBy = "herbBag")
    private Inventory inventory;

    private String name;

    private HashMap<String, Integer> herbContents;
    private HashMap<String, Integer> reagentContents;

    public HerbBag(){
        this.name = "Rough Homespun Herb Bag";
        this.herbContents = new HashMap<>();
        this.reagentContents = new HashMap<>();
    }

    public HerbBag(String name){
        this.name = name;
        this.herbContents = new HashMap<>();
        this.reagentContents = new HashMap<>();

    }

    public void forageForHerbs(){
        int herbsFound = rollDice(4);
        for(int i = 0 ; i < herbsFound ; i++){
            int tableRoll = rollDice(12);
            String herbName = fieldHerbTable(tableRoll);
            int quantityFound = rollDice(6);
            addHerbToBag(herbName, quantityFound);
        }
    }

    public void forageForReagents(){
        int reagentsFound = rollDice(6);
        for(int i = 0; i < reagentsFound ; i++){
            int tableRoll = rollDice(8);
            String reagentName = fieldReagentTable(tableRoll);
            int quantityFound = rollDice(6);
            addReagentToBag(reagentName, quantityFound);
        }
    }

    public void addHerbToBag(String herb, int quantity){
        this.herbContents.merge(herb, quantity, Integer::sum);
    }
    public void addReagentToBag(String reagent, int quantity){ this.reagentContents.merge(reagent, quantity, Integer::sum);}

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

    //create function to decrement kvpair
    //create function to delete kvpair if 0;


    //Getters
    public String getName() {
        return name;
    }
    public HashMap<String, Integer> getHerbContents() {
        return herbContents;
    }
    public HashMap<String, Integer> getReagentContents() {return reagentContents;}

    public List<String> getContentKeys(){
        ArrayList<String> keyList = new ArrayList<>(herbContents.keySet());
        return keyList;
    }
    public List<Integer> getContentValues(){
        ArrayList<Integer> valueList = new ArrayList<>(herbContents.values());
        return valueList;
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }
}
