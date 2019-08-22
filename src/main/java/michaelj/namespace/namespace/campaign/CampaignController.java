package michaelj.namespace.namespace.campaign;

import michaelj.namespace.namespace.account.AccountRepo;
import michaelj.namespace.namespace.account.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard")
public class CampaignController {

    @Autowired
    AccountRepo accountRepo;

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
}
