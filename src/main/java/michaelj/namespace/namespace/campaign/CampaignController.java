package michaelj.namespace.namespace.campaign;

import com.sun.org.apache.xpath.internal.operations.Mod;
import michaelj.namespace.namespace.account.AccountRepo;
import michaelj.namespace.namespace.account.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.swing.text.html.Option;
import java.security.Principal;
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
            @RequestParam String campaignWorld
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());

        Campaign newCampaign = new Campaign(campaignMoniker, campaignWorld, user);
        campaignRepo.save(newCampaign);
        user.addCampaign(newCampaign);

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
        }

        return "singleCampaignView";
    }

}
