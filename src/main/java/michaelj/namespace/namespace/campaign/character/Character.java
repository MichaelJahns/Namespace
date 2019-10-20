package michaelj.namespace.namespace.campaign.character;

import michaelj.namespace.namespace.campaign.Campaign;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Character {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Campaign campaign;

    private String name;
    private String description;
    private String location;
    private String speechMannerisms;
    public boolean isPC;

    public Character(){
        this.name = "Perceval Personson";
        this.description = "Heavily Powdered Parody of the Rich, self describes as a World Famous Magician";
        this.location = "Last Seen in Ordoa";
        this.speechMannerisms = "Really opens up about themselves, constantly seeking validation";
        this.isPC = false;
    }
    public Character(String name, String description, String location, String speech, boolean isPC){
        this.name = name;
        this.description = description;
        this.location = location;
        this.speechMannerisms = speech;
        this.isPC = isPC;
    }
//  Getters
    public Campaign getCampaign() {
        return campaign;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }
    public String getSpeechMannerisms() {
        return speechMannerisms;
    }
    public Boolean getPC() {
        return isPC;
    }
//    Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setSpeechMannerisms(String speechMannerisms) {
        this.speechMannerisms = speechMannerisms;
    }
    public void setPC(Boolean PC) {
        isPC = PC;
    }
}
