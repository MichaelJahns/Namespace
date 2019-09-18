package michaelj.namespace.namespace.inventory;

import michaelj.namespace.namespace.account.AccountRepo;
import michaelj.namespace.namespace.account.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    AccountRepo accountRepo;

    @GetMapping("/herbology")
    public String getHerbBag(
            Principal p,
            Model model
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        if(!user.getInventory().getHerbBag().getHerbContents().isEmpty()){
            model.addAttribute("herbKeys", user.getInventory().getHerbBag().getContentKeys());
            model.addAttribute("herbValues", user.getInventory().getHerbBag().getContentValues());
        }

        return "herbology";
    }


}
