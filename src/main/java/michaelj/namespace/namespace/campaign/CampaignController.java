package michaelj.namespace.namespace.campaign;

import michaelj.namespace.namespace.services.WOTCAPI.Response;
import michaelj.namespace.namespace.account.AccountRepo;
import michaelj.namespace.namespace.account.UserAccount;
import michaelj.namespace.namespace.campaign.character.Character;
import michaelj.namespace.namespace.campaign.character.CharacterRepo;
import michaelj.namespace.namespace.campaign.playerCharacter.PlayerCharacter;
import michaelj.namespace.namespace.campaign.playerCharacter.PlayerCharacterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static michaelj.namespace.namespace.services.WOTCAPI.gameEditionService.driver;


@Controller
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    CampaignRepo campaignRepo;

    @Autowired
    CharacterRepo characterRepo;

    @Autowired
    PlayerCharacterRepo playerCharacterRepo;

    @GetMapping()
    public String getCampaigns(
            Principal p,
            Model model
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        model.addAttribute("campaigns", user.getCampaigns());
        return "campaign";
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
            List<UserAccount> allUsers= accountRepo.findAll();
            List<Character> characters = singleCampaign.getCharacters();
            List<PlayerCharacter> playerCharacters = singleCampaign.getPlayerCharacters();

            Response allDNDClasses = driver();
            List charmer = allDNDClasses.getResults();

            model.addAttribute("campaign", singleCampaign);
            model.addAttribute("allUsers", allUsers);
            model.addAttribute("characters", characters);
            model.addAttribute("playerCharacters", playerCharacters);
            model.addAttribute("allDNDClasses", charmer);

        }

        return "singleCampaignView";
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
        if(isUserDM != null ){
            newCampaign = new Campaign(user, campaignMoniker, campaignWorld, user);
        }else{
            newCampaign = new Campaign(user, campaignMoniker, campaignWorld, null);
        }
        user.addCampaign(newCampaign);
        campaignRepo.save(newCampaign);
        accountRepo.save(user);

        return "redirect:/campaign";
    }

    @PostMapping("/newCharacter")
    public String createCharacter(
            Principal p,
            Model model,
            @RequestParam Long campaignID,
            @RequestParam String characterName,
            @RequestParam String characterDescription,
            @RequestParam String characterLocation,
            @RequestParam(required = false) String characterSpeech,
            @RequestParam(required = false) Boolean characterIsPC
    ){
        Optional<Campaign> foundCampaign = campaignRepo.findById(campaignID);
        boolean isPC = (characterIsPC != null ) ? true : false;
            if(foundCampaign.isPresent()){
            Campaign campaign = foundCampaign.get();
            Character newChar = new Character(characterName, characterDescription, characterLocation, characterSpeech, isPC);
            newChar.setNativeCampaign(campaign);
            characterRepo.save(newChar);
            campaignRepo.save(campaign);
        }

        String returnAddress = "redirect:/campaign/" + campaignID;
        return returnAddress;
    }

    @PostMapping("/newPlayerCharacter")
    public String createPlayerCharacter(
            Principal p,
            Model model,
            @RequestParam Long campaignId,
            @RequestParam String name,
            @RequestParam String race,
            @RequestParam String characterClass,
            @RequestParam String alignment,
            @RequestParam int level,
            @RequestParam int maxHP,
            @RequestParam int armorClass
            ){
        Optional<Campaign> foundCampaign = campaignRepo.findById(campaignId);
        if(foundCampaign.isPresent()) {
            Campaign campaign = foundCampaign.get();
            PlayerCharacter newPC = new PlayerCharacter(
                    name, race, characterClass, alignment,
                    level, maxHP, armorClass
            );
            newPC.setCampaign(campaign);

            playerCharacterRepo.save(newPC);
            campaignRepo.save(campaign);
        }
        String returnAddress = "redirect:/campaign/" + campaignId;
        return returnAddress;
    }
}
