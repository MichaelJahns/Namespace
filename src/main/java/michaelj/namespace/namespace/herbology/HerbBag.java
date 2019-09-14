package michaelj.namespace.namespace.herbology;

import michaelj.namespace.namespace.inventory.Inventory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static michaelj.namespace.namespace.board.Dice.rollDice;


@Entity
public class HerbBag {
    @GeneratedValue
    @Id
    private long id;

//    @OneToOne(mappedBy = "herbBag")
//    private Inventory inventory;

    private String name;
    private int capacity;

    private HashMap<String, Integer> stock;

    public HerbBag(){
        this.name = "Rough Homespun Herb Bag";
        this.capacity = 30;
        this.stock = new HashMap<>();
    }

    public HerbBag(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        this.stock = new HashMap<>();
    }

    public boolean checkHerbBag(String herb){
        Boolean output = this.stock.containsKey(herb);
        return output;
    }

    public void bigTester(){
        for(Map.Entry<String, Integer> entry : stock.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Set holder = stock.entrySet();
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
            addHerbToBag(reagentName, quantityFound);
        }
    }

    public void addHerbToBag(String herb, int quanity){
        this.stock.merge(herb, quanity, Integer::sum);
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

    private static String fieldReagentTable(int roll){
        String herb = "";
        switch (roll) {
            case 1:
                herb = "Snape Grass";
                break;
            case 2:
                herb = "Limwurt root";
                break;
            case 3:
                herb = "Eye of newt";
                break;
            case 4:
                herb = "Red Spider's Eyes";
                break;
            case 5:
                herb = "Mort Myre Fungus";
                break;
            case 6:
                herb = "Toad's Legs";
                break;
            case 7:
                herb = "Potato Cactus";
                break;
            case 8:
                herb = "Snail Slime";
                break;
        }
        return herb;
    }



    //create function to decrement kvpair
    //create function to delete kvpair if 0;


    //Getters
    public String getName() {
        return name;
    }
    public int getCapacity() {
        return capacity;
    }
    public HashMap<String, Integer> getStock() {
        return stock;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
