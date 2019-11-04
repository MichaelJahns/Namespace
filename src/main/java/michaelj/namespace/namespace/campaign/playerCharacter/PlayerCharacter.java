package michaelj.namespace.namespace.campaign.playerCharacter;

import michaelj.namespace.namespace.campaign.Campaign;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PlayerCharacter {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String race;
    private String characterClass;
    private String alignment;
    private int level;
    private int currentHp;
    private int maxHp;
    private int armorClass;
//    private List<String> notes;

    @ManyToOne
    private Campaign campaign;

    public PlayerCharacter(){

    }
    public PlayerCharacter(
            String name,
            String race,
            String characterClass,
            String alignment,
            int level,
            int maxHp,
            int armorClass){
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.alignment = alignment;
        this.level = level;
        this.currentHp = maxHp;
        this.maxHp = maxHp;
        this.armorClass = armorClass;
//        this.notes = new ArrayList();
    }

    //Getters
    public Long getId() {
        return id;
    }

    public Campaign getCampaign() {
        return campaign;
    }
//    public List getNotes() {
//        return notes;
//    }
    public int getArmorClass() {
        return armorClass;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public int getCurrentHp() {
        return currentHp;
    }
    public int getLevel() {
        return level;
    }
    public String getAlignment() {
        return alignment;
    }
    public String getCharacterClass() {
        return characterClass;
    }
    public String getRace() {
        return race;
    }
    public String getName() {
        return name;
    }

    //Setters
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
//    public void setNotes(List notes) {
//        this.notes = notes;
//    }
    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }
    public void setRace(String race) {
        this.race = race;
    }
    public void setName(String name) {
        this.name = name;
    }

}
