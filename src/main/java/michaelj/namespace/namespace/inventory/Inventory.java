package michaelj.namespace.namespace.inventory;

import michaelj.namespace.namespace.account.UserAccount;
import michaelj.namespace.namespace.inventory.alchemy.PotionSatchel;
import michaelj.namespace.namespace.inventory.herbology.ForageSatchel;

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
    private ForageSatchel forageSatchel;

    @OneToOne
    private PotionSatchel potionSatchel;

    public Inventory(){
        this.forageSatchel = new ForageSatchel();
        this.potionSatchel = new PotionSatchel();
    }

    public ForageSatchel getForageSatchel(){
        return this.forageSatchel;
    }
    public void setForageSatchel(ForageSatchel forageSatchel){
        this.forageSatchel = forageSatchel;
    }
    public PotionSatchel getPotionSatchel() {
        return potionSatchel;
    }
    public void setPotionSatchel(PotionSatchel potionSatchel) {
        this.potionSatchel = potionSatchel;
    }
}
