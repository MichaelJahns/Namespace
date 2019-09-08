package michaelj.namespace.namespace.campaign;

import michaelj.namespace.namespace.account.AccountRepo;
import michaelj.namespace.namespace.account.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard")
public class CampaignController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    CampaignRepo campaignRepo;

    @GetMapping()
    public String getDashboard(
            Principal p,
            Model model
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        return "dashboard";
    }

    @GetMapping("/campaign")
    public String getCampaigns(
            Principal p,
            Model model
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        return "campaign";
    }

    @PostMapping("/campaign")
    public RedirectView createEntry(
            @RequestParam String body,
            Principal p
    ) {
        UserAccount user = accountRepo.findByUsername(p.getName());
        Campaign campaign = new Campaign(body);
        campaignRepo.save(campaign);
        System.out.println("Critical SUccess");
        return new RedirectView("/home");
    }
}
