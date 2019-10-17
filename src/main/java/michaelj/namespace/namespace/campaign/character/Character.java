package michaelj.namespace.namespace.campaign.character;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.swing.*;

@Entity
public class Character {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String description;
    private String location;
    private String speechMannerisms;
    private Boolean isNPC;

    public Character(String name, String description, String location, String speech){
        this.name = name;
        this.description = description;
        this.location = location;
        this.speechMannerisms = speech;
    }
}
