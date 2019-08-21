package michaelj.namespace.namespace.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @Autowired
    AccountRepo repo;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/signup")
    public String signup() {return "signUp"; }

    @PostMapping("/signup")
    public String signup(
            @RequestParam String username,
            @RequestParam String password
    ) throws DataIntegrityViolationException {
        UserAccount newUser = new UserAccount();
        newUser.setUsername(username);
        newUser.setPassword(password, this.encoder);

        repo.save(newUser);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                newUser,
                null,
                newUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(token);
        return "redirect:/home";
    }
}
