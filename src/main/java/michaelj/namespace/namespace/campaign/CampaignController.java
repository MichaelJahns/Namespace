package michaelj.namespace.namespace.campaign;

import michaelj.namespace.namespace.account.AccountRepo;
import michaelj.namespace.namespace.account.UserAccount;
import michaelj.namespace.namespace.campaign.character.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    CampaignRepo campaignRepo;

    @GetMapping()
    public String getCampaigns(
            Principal p,
            Model model
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());

        model.addAttribute("campaigns", user.getCampaigns());
        return "campaign";
    }

    @PostMapping("/createCampaign")
    public String createCampaign(
            Principal p,
            Model model,
            @RequestParam String campaignMoniker,
            @RequestParam String campaignWorld,
            @RequestParam(required = false) Boolean isUserDM
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        Campaign newCampaign;
        if(isUserDM){
            newCampaign = new Campaign(user, campaignMoniker, campaignWorld, user);
        }else{
            newCampaign = new Campaign(user, campaignMoniker, campaignWorld);

        }
        user.addCampaign(newCampaign);
        campaignRepo.save(newCampaign);
        accountRepo.save(user);

        return "redirect:/campaign";
    }

    @GetMapping("/{id}")
    public String getSingleCampaign(
            Principal p,
            Model model,
            @PathVariable Long id
    ){
        UserAccount user = accountRepo.findByUsername(p.getName());
        Optional<Campaign> foundCampaign = campaignRepo.findById(id);

        if(foundCampaign.isPresent()){
            Campaign singleCampaign = foundCampaign.get();
            model.addAttribute("campaign", singleCampaign);
            List<UserAccount> allUsers= accountRepo.findAll();
            model.addAttribute("allUsers", allUsers);
        }

        return "singleCampaignView";
    }

    @PostMapping("/newCharacter")
    public String createCharacter(
            Principal p,
            Model model,
            @RequestParam String characterName,
            @RequestParam String characterDescription,
            @RequestParam String characterLocation,
            @RequestParam String characterSpeech,
            @RequestParam Boolean characterIsNPC
    ){
        Character newChar = new Character(characterName, characterDescription, characterLocation, characterSpeech, characterIsNPC);
        return "redirect:/campaign";
    }

}
