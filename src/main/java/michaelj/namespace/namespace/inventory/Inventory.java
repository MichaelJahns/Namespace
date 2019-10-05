package michaelj.namespace.namespace.inventory;

import michaelj.namespace.namespace.account.UserAccount;
import michaelj.namespace.namespace.inventory.alchemy.PotionSatchel;
import michaelj.namespace.namespace.inventory.herbology.HerbBag;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Inventory {
    @GeneratedValue
    @Id
    private long id;

    @OneToOne(mappedBy = "inventory")
    private UserAccount account;

    @OneToOne
    private HerbBag herbBag;

    @OneToOne
    private PotionSatchel potionSatchel;

    public Inventory(){
        this.herbBag = new HerbBag();
        this.potionSatchel = new PotionSatchel();
    }

    public HerbBag getHerbBag(){
        return this.herbBag;
    }
    public void setHerbBag(HerbBag herbBag){
        this.herbBag = herbBag;
    }

    public PotionSatchel getPotionSatchel() {
        return potionSatchel;
    }

    public void setPotionSatchel(PotionSatchel potionSatchel) {
        this.potionSatchel = potionSatchel;
    }
}
