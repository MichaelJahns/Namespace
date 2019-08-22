package michaelj.namespace.namespace.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AccountController {

    @Autowired
    AccountRepo repo;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/login")
    public String getLogin(
            @RequestParam(required = false) boolean errored,
            Principal p,
            Model model
    ) {
        try {
            UserAccount user = repo.findByUsername(p.getName());
            if (user != null) {
                return "redirect:/dashboard/";
            }
        }catch (Exception E){
            //Todo: what is the fail state?
//            model.addAttribute("errored", true);
        }
        return "login";
    }

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
        return "redirect:/dashboard";
    }
}
