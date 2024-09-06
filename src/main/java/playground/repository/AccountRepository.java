package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.model.Account;
import playground.model.TheCheat;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
