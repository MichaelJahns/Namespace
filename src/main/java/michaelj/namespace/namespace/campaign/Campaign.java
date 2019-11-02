package michaelj.namespace.namespace.campaign;

import michaelj.namespace.namespace.account.UserAccount;
import michaelj.namespace.namespace.campaign.character.Character;
import michaelj.namespace.namespace.campaign.playerCharacter.PlayerCharacter;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Campaign {
    @Id
    @GeneratedValue
    private long id;

    private String moniker;
    private String world;

    @ManyToOne
    private UserAccount createdBy;
    @ManyToOne
    private UserAccount dungeonMaster;

    @OneToMany(mappedBy = "campaign")
    private List<PlayerCharacter> playerCharacters;

    @OneToMany(mappedBy = "nativeCampaign")
    private List<Character> characters;


    public Campaign() {
        this.playerCharacters = new ArrayList<>();
    }

    public Campaign(UserAccount createdBy, String moniker, String world){
        this.createdBy = createdBy;
        this.moniker = moniker;
        this.world = world;
        this.dungeonMaster = null;
        this.playerCharacters = new ArrayList<>();
    }

    public Campaign(UserAccount createdBy, String moniker, String world, UserAccount DM){
        this.createdBy = createdBy;
        this.moniker = moniker;
        this.world = world;
        this.dungeonMaster = DM;
        this.playerCharacters = new ArrayList<>();
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
    public UserAccount getDungeonMaster() {
        return dungeonMaster;
    }
    public List<PlayerCharacter> getPlayerCharacters() {
        return playerCharacters;
    }
    public List<Character> getCharacters() {
        return characters;
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
    public void setDungeonMaster(UserAccount dungeonMaster) {
        this.dungeonMaster = dungeonMaster;
    }
    public void setPlayerCharacters(List<PlayerCharacter> playerCharacters) {
        this.playerCharacters = playerCharacters;
    }
    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }






}
