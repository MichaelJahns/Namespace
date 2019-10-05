package michaelj.namespace.namespace.campaign;

import michaelj.namespace.namespace.account.AccountRepo;
import michaelj.namespace.namespace.account.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Request;

import java.security.Principal;

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

}
