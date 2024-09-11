package playground.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import playground.dto.AccountOftenDTO;
import playground.model.Account;
import playground.model.Type;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountNumber(String accountNumber);

    @Query("SELECT a FROM Account a WHERE a.isDepositOrWithdrawal = :type AND a.user.id = :userId ORDER BY a.dateTime DESC")
    List<Account> findByTypeAndUserId(@Param("type") Type type, @Param("userId") Long userId, Pageable pageable);;

    @Query(value = "select a.fiance_id, a.account_number, a.id, u.user_name, innerAc.count "
            + "from account a inner join users u on a.user_id=u.id inner join "
            + "(select account_number, Count(account_number) as count from account where is_deposit_or_withdrawal=:type and user_id=:userId and date_time > now() - INTERVAL 3 MONTH group by account_number ) as innerAc on innerAc.account_number=a.account_number "
            + "where a.is_deposit_or_withdrawal=:type and user_id=:userId and date_time > now() - INTERVAL 3 MONTH order by innerAc.count desc", nativeQuery = true)
    List<Object[]> findByTypeOrderByCountDescNativeQuery(@Param("type") String type,@Param("userId") Long userId, Pageable pageable);
}
