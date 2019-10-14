package michaelj.namespace.namespace.account;

import michaelj.namespace.namespace.campaign.Campaign;
import michaelj.namespace.namespace.inventory.Inventory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class UserAccount implements UserDetails {

    @GeneratedValue
    @Id
    private long id;
    @Column(unique = true)
    private String username;
    private String password;

    @OneToOne
    private Inventory inventory;

    @OneToMany (mappedBy = "createdBy")
    private List<Campaign> campaigns;

    @OneToMany (mappedBy = "dungeonMaster")
    private List<Campaign> dungeonMasters;

    public UserAccount(){}

    public UserAccount(String username, String password, PasswordEncoder encoder){
        this.username = username;
        this.password = encoder.encode(password);
        this.inventory = new Inventory();
        this.campaigns = new ArrayList<>();
    }

    public void addCampaign(Campaign campaign){
        this.campaigns.add(campaign);
    }

    //Getters
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    public Inventory getInventory() {
        return inventory;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //Setters
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password, PasswordEncoder encoder){
        this.password = encoder.encode(password);
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  return true;  }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}
