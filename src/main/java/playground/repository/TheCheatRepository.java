package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import playground.model.TheCheat;

import java.util.List;

@Repository
public interface TheCheatRepository extends JpaRepository<TheCheat, Long> {

    @Query("SELECT tc FROM TheCheat tc WHERE tc.account.accountNumber = :accountNumber")
    List<TheCheat> findByAccountNumber(@Param("accountNumber") String accountNumber);

    List<TheCheat> findByAccount_AccountNumber(String accountNumber);
}
