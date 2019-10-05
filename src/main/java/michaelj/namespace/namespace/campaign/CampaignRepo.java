package michaelj.namespace.namespace.campaign;

import michaelj.namespace.namespace.account.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepo extends JpaRepository<Campaign, Long> {
}
