package michaelj.namespace.namespace.inventory;

import michaelj.namespace.namespace.account.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @GetMapping("/herbology")
    public String getCampaigns(
            Principal p,
            Model model
    ){
        return "herbology";
    }


}
