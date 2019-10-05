package michaelj.namespace.namespace.campaign;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@Entity
public class Campaign {
    @Id
    @GeneratedValue
    private long id;
    private String gameMaster;
    private ArrayList<String> players;

//    @ManyToOne
//    private long userAccount;

    public Campaign(){
        players = new ArrayList<>();
    }
    public Campaign(String gameMaster){
        players=new ArrayList<>();
        this.gameMaster = gameMaster;
    }


//    public long getUserAccount() {
//        return userAccount;
//    }
//
//    public void setUserAccount(long userAccount) {
//        this.userAccount = userAccount;
//    }
}
