package michaelj.namespace.namespace.inventory;

import michaelj.namespace.namespace.account.UserAccount;
import michaelj.namespace.namespace.herbology.HerbBag;
import org.springframework.beans.factory.annotation.Autowired;

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

//    @OneToOne
//    private HerbBag herbBag;
//
//    public Inventory(){
//        this.herbBag = new HerbBag();
//    }
//
//    public HerbBag getHerbBag(){
//        return this.herbBag;
//    }
//    public void setHerbBag(HerbBag herbBag){
//        this.herbBag = herbBag;
//    }
}
