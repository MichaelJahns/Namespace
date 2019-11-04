package michaelj.namespace.namespace.account;

import michaelj.namespace.namespace.inventory.alchemy.PotionSatchel;
import michaelj.namespace.namespace.inventory.alchemy.PotionSatchelRepo;
import michaelj.namespace.namespace.inventory.herbology.ForageSatchel;
import michaelj.namespace.namespace.inventory.herbology.ForageSatchelRepo;
import michaelj.namespace.namespace.inventory.Inventory;
import michaelj.namespace.namespace.inventory.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    AccountRepo accountRepo;

    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    ForageSatchelRepo forageSatchelRepo;

    @Autowired
    PotionSatchelRepo potionSatchelRepo;

    @Autowired
    PasswordEncoder encoder;

    public void saveAll(ForageSatchel forageSatchel, PotionSatchel potionSatchel, Inventory inventory, UserAccount userAccount){
        forageSatchelRepo.save(forageSatchel);
        potionSatchelRepo.save(potionSatchel);
        inventoryRepo.save(inventory);
        accountRepo.save(userAccount);
    }

    @GetMapping("/login")
    public String getLogin(
            @RequestParam(required = false) boolean errored,
            Principal p,
            Model model
    ) {
        try {
            UserAccount user = accountRepo.findByUsername(p.getName());
            if (user != null) {
                return "redirect:/dashboard/";
            }
        }catch (Exception E){
            //Todo: what is the fail state?
            //TODO: When a user attempts to log in with a wrong account, ERROR
            //TODO: When a user attempts to log in with the wrong password, ERROR
//            model.addAttribute("errored", true);
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {return "signUp"; }

    @PostMapping("/signup")
    public String signup(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) throws DataIntegrityViolationException {

        UserAccount check = accountRepo.findByUsername(username);
        if(check == null){
            UserAccount newUser = new UserAccount(username, password, this.encoder);
            Inventory inventory = newUser.getInventory();
            ForageSatchel forageSatchel = inventory.getForageSatchel();
            PotionSatchel potionSatchel = inventory.getPotionSatchel();
            saveAll(forageSatchel, potionSatchel, inventory, newUser);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    newUser,
                    null,
                    newUser.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(token);
            return "redirect:/dashboard";
        } else{
            model.addAttribute("errored", true);
            return "signUp";
        }
    }

    @GetMapping("/account")
    public String getAccountPage(
            Principal p,
            Model model
    ){
        UserAccount user = accountRepo.findByUsername(p.getName());
        model.addAttribute("user", user);
        return "account";
    }

}
