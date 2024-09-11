package playground.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import playground.dto.AccountOftenDTO;
import playground.model.Account;
import playground.model.TheCheat;
import playground.model.Type;
import playground.repository.AccountRepository;
import playground.repository.TheCheatRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {
    private final TheCheatRepository theCheatRepository;
    private final AccountRepository accountRepository;


    public String searchAccount(String accountNumber){
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if(account == null){
            return "없는 계좌번호 입니다.";
        }
        List<TheCheat> theCheatsAccountNumber = theCheatRepository.findByAccount_AccountNumber(accountNumber);

        if(theCheatsAccountNumber.isEmpty()) return "정상계좌 입니다.";
        return "사기꾼 계좌 입니다.";
    }

    public boolean searchAccountApi(String accountNumber){
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if(account == null){
            return false;
        }
        List<TheCheat> theCheatsAccountNumber = theCheatRepository.findByAccount_AccountNumber(accountNumber);

        if(theCheatsAccountNumber.isEmpty()) return false;
        return true;
    }
    public List<Account> findByAllAccount(){
        return accountRepository.findAll();
    }

    public List<TheCheat> findByTheCheatLog(String accountNumber){
        return theCheatRepository.findByAccount_AccountNumber(accountNumber);

    }

    public List<Account> findByOrderByDateTimeDesc(Long userId){
        Pageable limitPage = PageRequest.of(0, 5);
        return accountRepository.findByTypeAndUserId(Type.Deposit,userId, limitPage);
    }

    public List<Object[]> findAccountOftenDTO(Long id){
        Pageable limitPage = PageRequest.of(0, 5);
        return accountRepository.findByTypeOrderByCountDescNativeQuery("Deposit", id, limitPage);
    }
}
