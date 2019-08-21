package michaelj.namespace.namespace.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
