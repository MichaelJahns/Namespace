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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    AccountRepo accountRepo;

    @GetMapping()
    public String getDashboard(
            Principal p,
            Model model
    ){
        UserAccount user = this.accountRepo.findByUsername(p.getName());
        List<Campaign> campaigns = user.getCampaigns();
        model.addAttribute("campaigns", campaigns);
        return "dashboard";
    }


}
