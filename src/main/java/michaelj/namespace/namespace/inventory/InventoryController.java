package michaelj.namespace.namespace.inventory;

import michaelj.namespace.namespace.account.AccountRepo;
import michaelj.namespace.namespace.account.UserAccount;
import michaelj.namespace.namespace.herbology.HerbBag;
import michaelj.namespace.namespace.herbology.HerbBagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.soap.SOAPBinding;
import java.security.Principal;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    HerbBagRepo herbBagRepo;

    @GetMapping("/herbology")
    public String getHerbBag(
            Principal p,
            Model model
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        HerbBag forageSatchel = user.getInventory().getHerbBag();

        model.addAttribute("herbs", forageSatchel.getHerbs());
        model.addAttribute("reagents", forageSatchel.getReagents());

        return "herbology";
    }

    @GetMapping("/forage")
    public String forage(
            Principal p,
            Model model
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        HerbBag forageSatchel = user.getInventory().getHerbBag();
        forageSatchel.forageForHerbs();
        forageSatchel.forageForReagents();
        this.herbBagRepo.save(forageSatchel);

        model.addAttribute("herbs", forageSatchel.getHerbs());
        model.addAttribute("reagents", forageSatchel.getReagents());

        return "redirect:/inventory/herbology";
    }

}
