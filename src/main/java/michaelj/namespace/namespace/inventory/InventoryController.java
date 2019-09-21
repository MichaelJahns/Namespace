package michaelj.namespace.namespace.inventory;

import michaelj.namespace.namespace.account.AccountRepo;
import michaelj.namespace.namespace.account.UserAccount;
import michaelj.namespace.namespace.herbology.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.soap.SOAPBinding;
import java.security.Principal;

import static michaelj.namespace.namespace.board.Dice.rollDice;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    HerbBagRepo herbBagRepo;

    @Autowired
    HerbRepo herbRepo;

    @Autowired
    ReagentRepo reagentRepo;

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

        int herbsFound = rollDice(6);
        for(int i = 0; i < herbsFound; i++) {
            Herb foundHerb = new Herb();
            Herb stockedHerb = forageSatchel.getHerbByName(foundHerb.getHerbName());
            if (stockedHerb != null) {
                stockedHerb.incrementQuantity(foundHerb.getQuantity());
                herbRepo.save(stockedHerb);
            } else {
                foundHerb.setHerbPouch(forageSatchel);
                herbRepo.save(foundHerb);
                forageSatchel.addHerb(foundHerb);
            }
        }

        int reagentsFound = rollDice(4);
        for(int i = 0; i < reagentsFound; i++){
            Reagent foundReagent = new Reagent();
            Reagent stockedReagent = forageSatchel.getReagentByName(foundReagent.getReagentName());
            if(stockedReagent != null) {
                stockedReagent.incrementQuantity(foundReagent.getQuantity());
                reagentRepo.save(stockedReagent);
            } else{
                foundReagent.setReagentPouch(forageSatchel);
                reagentRepo.save(foundReagent);
                forageSatchel.addReagent(foundReagent);
            }
        }

        forageSatchel = herbBagRepo.save(forageSatchel);



        model.addAttribute("herbs", forageSatchel.getHerbs());
        model.addAttribute("reagents", forageSatchel.getReagents());

        return "redirect:/inventory/herbology";
    }

}
