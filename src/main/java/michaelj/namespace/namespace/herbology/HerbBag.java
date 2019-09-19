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

    private List<Ingredient> herbs;
    private List<Ingredient> reagents;

    public HerbBag(){
        this.name = "Rough Homespun Ingredient Bag";
        this.herbs = new ArrayList<>();
        this.reagents = new ArrayList<>();
    }

    public HerbBag(String name){
        this.name = name;
        this.herbs = new ArrayList<>();
        this.reagents = new ArrayList<>();

    }

    public void forageForHerbs(){
        int herbsFound = rollDice(4);
        for(int i = 0 ; i < herbsFound ; i++){
            int tableRoll = rollDice(12);
            String herbName = fieldHerbTable(tableRoll);
            int quantityFound = rollDice(6);
            storeInHerbologyBag(herbName, quantityFound, false);
        }
    }

    public void forageForReagents(){
        int reagentsFound = rollDice(6);
        for(int i = 0; i < reagentsFound ; i++){
            int tableRoll = rollDice(8);
            String reagentName = fieldReagentTable(tableRoll);
            int quantityFound = rollDice(6);
            storeInHerbologyBag(reagentName, quantityFound, true);
        }
    }

    public void storeInHerbologyBag(String ingredientName, int quantity, boolean isReagent){
        Ingredient newIngredient = new Ingredient(ingredientName, quantity, isReagent);
        if(isReagent){
            reagents.add(newIngredient);
        } else{
            herbs.add(newIngredient);
        }
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
    public List<Ingredient> getHerbs() {
        return herbs;
    }
    public List<Ingredient> getReagents() {
        return reagents;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHerbs(List<Ingredient> herbs) {
        this.herbs = herbs;
    }

    public void setReagents(List<Ingredient> reagents) {
        this.reagents = reagents;
    }
}
