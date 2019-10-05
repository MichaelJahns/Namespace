package michaelj.namespace.namespace.campaign;

import michaelj.namespace.namespace.account.UserAccount;

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

    @ManyToOne
    private UserAccount userAccount;

    private String moniker;
    private String world;
    private ArrayList<String> players;

    public Campaign() {
        this.players = new ArrayList<>();
    }

    public Campaign(String moniker, String world, UserAccount userAccount){
        this.moniker = moniker;
        this.world = world;
        this.players = new ArrayList<>();
        this.userAccount = userAccount;
    }


//  Getters
    public long getId() {
        return id;
    }
    public UserAccount getUserAccount() {
        return userAccount;
    }
    public String getMoniker() {
        return moniker;
    }
    public String getWorld() {
        return world;
    }
    public ArrayList<String> getPlayers() {
        return players;
    }

//  Setters
    public void setId(long id) {
        this.id = id;
    }
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    public void setMoniker(String moniker) {
        this.moniker = moniker;
    }
    public void setWorld(String world) {
        this.world = world;
    }
    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }
}
