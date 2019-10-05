package michaelj.namespace.namespace.inventory;

import michaelj.namespace.namespace.account.AccountRepo;
import michaelj.namespace.namespace.account.UserAccount;
import michaelj.namespace.namespace.inventory.herbology.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

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

    @PostMapping("/addHerb")
    public String addHerb(
        Principal p,
        Model m,
        @RequestParam String herbName,
        @RequestParam int quantity
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        HerbBag herbBag = user.getInventory().getHerbBag();

        Herb stockedHerb = herbBag.getHerbByName(herbName);

        if(stockedHerb != null){
            stockedHerb.incrementQuantity(quantity);
            herbRepo.save(stockedHerb);
        } else{
            Herb manualHerb = new Herb(herbName, quantity, herbBag);
            herbRepo.save(manualHerb);
            herbBag.addHerb(manualHerb);
        }

        herbBagRepo.save(herbBag);
        return "redirect:/inventory/herbology";
    }

    @PutMapping("/incrementHerb/{id}")
    public String incrementHerb(
            Principal p,
            Model model,
            @PathVariable Long id
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        HerbBag herbBag = user.getInventory().getHerbBag();

        Optional<Herb> repoHerb = herbRepo.findById(id);
        if(repoHerb.isPresent()){
            Herb editHerb = repoHerb.get();
            editHerb.incrementQuantity(1);
            if(editHerb.getQuantity() >= 99){
                editHerb.setQuantity(99);
            }
            herbRepo.save(editHerb);
        }
        herbBagRepo.save(herbBag);

        return "redirect:/inventory/herbology";
    }

    @PutMapping("/decrementHerb/{id}")
    public String decrementHerb(
            Principal p,
            Model model,
            @PathVariable Long id
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        HerbBag herbBag = user.getInventory().getHerbBag();

        Optional<Herb> repoHerb = herbRepo.findById(id);
        if(repoHerb.isPresent()){
            Herb editHerb = repoHerb.get();
            editHerb.decrementQuantity(1);
            if(editHerb.getQuantity() <= 0){
                editHerb.setHerbPouch(null);
                herbBag.getHerbs().remove(editHerb);
            }else{
                herbRepo.save(editHerb);
            }
        }else{
            System.out.println("opps");
        }

        herbBagRepo.save(herbBag);

        return "redirect:/inventory/herbology";
    }

    @DeleteMapping("/deleteHerb/{id}")
    public String deleteHerb(
            Principal p,
            Model model,
            @PathVariable Long id
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        HerbBag herbBag = user.getInventory().getHerbBag();

        Optional<Herb> repoHerb = herbRepo.findById(id);
        if(repoHerb.isPresent()){
            Herb deleteHerb = repoHerb.get();
            deleteHerb.setHerbPouch(null);
            herbBag.getHerbs().remove(deleteHerb);
            herbRepo.delete(deleteHerb);
        }

        herbBagRepo.save(herbBag);

        return "redirect:/inventory/herbology";
    }

    @PostMapping("/addReagent")
    public String addReagent(
            Principal p,
            Model m,
            @RequestParam String reagentName,
            @RequestParam int quantity
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        HerbBag herbBag = user.getInventory().getHerbBag();

        Reagent stockedReagent = herbBag.getReagentByName(reagentName);

        if(stockedReagent != null){
            stockedReagent.incrementQuantity(quantity);
            reagentRepo.save(stockedReagent);
        } else{
            Reagent manualReagent = new Reagent(reagentName, quantity, herbBag);
            reagentRepo.save(manualReagent);
            herbBag.addReagent(manualReagent);
        }

        herbBagRepo.save(herbBag);
        return "redirect:/inventory/herbology";
    }

    @PutMapping("/incrementReagent/{id}")
    public String incrementReagent(
            Principal p,
            Model model,
            @PathVariable Long id
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        HerbBag herbBag = user.getInventory().getHerbBag();

        Optional<Reagent> repoReagent = reagentRepo.findById(id);
        if(repoReagent.isPresent()){
            Reagent editReagent = repoReagent.get();
            editReagent.incrementQuantity(1);
            if(editReagent.getQuantity() >= 99){
                editReagent.setQuantity(99);
            }
            reagentRepo.save(editReagent);
        }
        herbBagRepo.save(herbBag);

        return "redirect:/inventory/herbology";
    }

    @PutMapping("/decrementReagent/{id}")
    public String decrementReagent(
            Principal p,
            Model model,
            @PathVariable Long id
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        HerbBag herbBag = user.getInventory().getHerbBag();

        Optional<Reagent> repoReagent = reagentRepo.findById(id);
        if(repoReagent.isPresent()){
            Reagent editReagent = repoReagent.get();
            editReagent.decrementQuantity(1);
            if(editReagent.getQuantity() <= 0){
                editReagent.setReagentPouch(null);
                herbBag.getReagents().remove(editReagent);
            }else{
                reagentRepo.save(editReagent);
            }
        }else{
            System.out.println("opps");
        }

        herbBagRepo.save(herbBag);

        return "redirect:/inventory/herbology";
    }

    @DeleteMapping("/deleteReagent/{id}")
    public String deleteReagent(
            Principal p,
            Model model,
            @PathVariable Long id
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        HerbBag herbBag = user.getInventory().getHerbBag();

        Optional<Reagent> repoReagent = reagentRepo.findById(id);
        if(repoReagent.isPresent()){
            Reagent deleteReagent = repoReagent.get();
            deleteReagent.setReagentPouch(null);
            herbBag.getReagents().remove(deleteReagent);
            reagentRepo.delete(deleteReagent);
        }

        herbBagRepo.save(herbBag);

        return "redirect:/inventory/herbology";
    }
}
