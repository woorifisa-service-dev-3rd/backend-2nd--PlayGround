package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import playground.model.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountNumber(String accountNumber);
}
