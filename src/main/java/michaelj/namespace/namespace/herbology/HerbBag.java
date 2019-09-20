package michaelj.namespace.namespace.herbology;

import michaelj.namespace.namespace.inventory.Inventory;

import javax.persistence.*;
import java.util.*;

import static michaelj.namespace.namespace.board.Dice.rollDice;


@Entity
public class HerbBag {
    @GeneratedValue
    @Id
    private long id;

    @OneToOne(mappedBy = "herbBag")
    private Inventory inventory;


    @OneToMany(mappedBy = "herbPouch")
    private List<Herb> herbs;
    @OneToMany(mappedBy = "reagentPouch")
    private List<Reagent> reagents;

    public HerbBag(){
        this.herbs = new ArrayList<>();
        this.reagents = new ArrayList<>();
    }

    public HerbBag(String name){
        this.herbs = new ArrayList<>();
        this.reagents = new ArrayList<>();

    }

    public Herb isHerbPresent(String name){
        for(Herb herb : this.herbs){
            if(herb.getHerbName() == name){
                return herb;
            }
        }
        return null;
    }

    public Reagent isReagentPresent(String name){
        for(Reagent reagent: this.reagents){
            if(reagent.getReagentName() == name){
                return reagent;
            }
        }
        return null;
    }

    public void addHerb(Herb herb){
        Herb foundHerb = isHerbPresent(herb.getHerbName());
        if(foundHerb != null){
            foundHerb.incrementQuantity(herb.getQuantity());
        } else{
            this.herbs.add(herb);
        }
    }

    public void addReagent(Reagent reagent){
        Reagent foundReagent = isReagentPresent(reagent.getReagentName());
        if(foundReagent != null){
            foundReagent.incrementQuantity(reagent.getQuantity());
        } else {
            this.reagents.add(reagent);
        }
    }



    //create function to decrement kvpair
    //create function to delete kvpair if 0;


    //Getters
    public List<Herb> getHerbs() {
        return herbs;
    }
    public List<Reagent> getReagents() {
        return reagents;
    }

    //Setters

    public void setHerbs(List<Herb> herbs) {
        this.herbs = herbs;
    }

    public void setReagents(List<Reagent> reagents) {
        this.reagents = reagents;
    }
}
