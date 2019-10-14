package michaelj.namespace.namespace.inventory.herbology;

import michaelj.namespace.namespace.inventory.Inventory;

import javax.persistence.*;
import java.util.*;

// TODO: refactor to forageSatchel
// TODO: alphateize method, incorporate into save routes
@Entity
public class ForageSatchel {
    @GeneratedValue
    @Id
    private long id;

    @OneToOne(mappedBy = "forageSatchel")
    private Inventory inventory;


    @OneToMany(mappedBy = "herbPouch")
    private List<Herb> herbs;
    @OneToMany(mappedBy = "reagentPouch")
    private List<Reagent> reagents;

    public ForageSatchel(){
        this.herbs = new ArrayList<>();
        this.reagents = new ArrayList<>();
    }

    public ForageSatchel(String name){
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

    //Getters
    public List<Herb> getHerbs() {
//        alphabetize first
//        rank by rarity too?
        return herbs;
    }
    public List<Reagent> getReagents() {
        return reagents;
    }

    public Herb getHerbByName(String herbName){
        for(Herb stockedHerb : this.herbs){
            if(stockedHerb.getHerbName().equals(herbName)) {
                return stockedHerb;
            }
        }
        return null;
    }
    public Reagent getReagentByName(String reagentName){
        for(Reagent stockedReagent : this.reagents){
            if(stockedReagent.getReagentName().equals(reagentName)){
                return stockedReagent;
            }
        }
        return null;
    }

    //Setters

    public void setHerbs(List<Herb> herbs) {
        this.herbs = herbs;
    }

    public void setReagents(List<Reagent> reagents) {
        this.reagents = reagents;
    }
}
