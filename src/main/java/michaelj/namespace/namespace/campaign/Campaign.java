package michaelj.namespace.namespace.campaign;

import michaelj.namespace.namespace.account.UserAccount;
import org.springframework.security.core.userdetails.User;

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
    private UserAccount createdBy;
    private String moniker;
    private String world;
    @ManyToOne
    private UserAccount dungeonMaster;
    private ArrayList<String> players;

    public Campaign() {
        this.players = new ArrayList<>();
    }

    public Campaign(UserAccount createdBy, String moniker, String world){
        this.createdBy = createdBy;
        this.moniker = moniker;
        this.world = world;
        this.dungeonMaster = null;
        this.players = new ArrayList<>();
    }

    public Campaign(UserAccount createdBy, String moniker, String world, UserAccount DM){
        this.createdBy = createdBy;
        this.moniker = moniker;
        this.world = world;
        this.dungeonMaster = DM;
        this.players = new ArrayList<>();
    }

    //  Getters
    public long getId() {
        return id;
    }
    public UserAccount getCreatedBy() {
        return createdBy;
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
    public UserAccount getDungeonMaster() {
        return dungeonMaster;
    }

    //  Setters
    public void setId(long id) {
        this.id = id;
    }
    public void setCreatedBy(UserAccount createdBy) {
        this.createdBy = createdBy;
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
    public void setDungeonMaster(UserAccount dungeonMaster) {
        this.dungeonMaster = dungeonMaster;
    }
}
