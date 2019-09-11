package michaelj.namespace.namespace.herbology;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static michaelj.namespace.namespace.board.Dice.rollDice;

public class HerbBag {
    private String name;
    private int capacity;

    private HashMap<String, Integer> stock;

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
            // Roll dice to determine which Herb to pull from Loot Tables
                // Town, Field, Cave, Mountain, Ocean
            String herbName = "Shatter Stalk";
            int quantityFound = rollDice(6);
            addHerbToBag(herbName, quantityFound);
        }
    }

    public void forageForReagents(){
        int reagentsFound = rollDice(6);
        for(int i = 0; i < reagentsFound ; i++){
            //Roll dice to determine Which Reagents to pull from Loot Tables
                // Town, Field, Cave, Mountain, Ocean
            String reagentName = "Snape Grass";
            int quantityFound = rollDice(6);
            addHerbToBag(reagentName, quantityFound);
        }
    }

    public void addHerbToBag(String herb, int quanity){
        this.stock.merge(herb, quanity, Integer::sum);
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
