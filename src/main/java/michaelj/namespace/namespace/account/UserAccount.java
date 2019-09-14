package michaelj.namespace.namespace.account;

import michaelj.namespace.namespace.herbology.HerbBag;
import michaelj.namespace.namespace.inventory.Inventory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;

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

    public UserAccount(){}

    public UserAccount(String username, String password, PasswordEncoder encoder){
        this.username = username;
        this.password = encoder.encode(password);
        this.inventory = new Inventory();
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


}
