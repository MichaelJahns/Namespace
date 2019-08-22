package michaelj.namespace.namespace.account;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

@Entity
public class UserAccount implements UserDetails {

    @GeneratedValue
    @Id
    private long id;
    @Column(unique = true)
    private String username;
    private String password;

    public UserAccount(){}

    public UserAccount(String username, String password, PasswordEncoder encoder){
        this.username = username;
        this.password = encoder.encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password, PasswordEncoder encoder){
        this.password = encoder.encode(password);
    }
    @Override
    public String getUsername() {
        return username;
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
