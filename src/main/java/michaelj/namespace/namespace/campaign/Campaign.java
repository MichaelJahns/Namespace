package michaelj.namespace.namespace.campaign;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Campaign {
    @Id
    @GeneratedValue
    private long id;
    private String gameMaster;
    private ArrayList<String> players;

    public Campaign(){
        players = new ArrayList<>();
    }
    public Campaign(String gameMaster){
        players=new ArrayList<>();
        this.gameMaster = gameMaster;
    }


}
