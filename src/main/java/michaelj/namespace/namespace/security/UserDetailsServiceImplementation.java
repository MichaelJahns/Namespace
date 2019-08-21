package michaelj.namespace.namespace.security;

import michaelj.namespace.namespace.account.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username){
        return accountRepo.findByUsername(username);
    }
}
