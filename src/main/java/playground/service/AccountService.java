package playground.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import playground.dto.AccountOftenDTO;
import playground.model.*;
import playground.repository.AccountRepository;
import playground.repository.FianceRepository;
import playground.repository.TheCheatRepository;
import playground.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {
    private final TheCheatRepository theCheatRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final FianceRepository fianceRepository;


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



    public Boolean isUserinfo(String name){
        return userRepository.existsByUserName(name);
    }

    public User findUser(String name){
        return userRepository.findByUserName(name);
    }

    public Boolean isAccountinfo(String accountnumber){
        return accountRepository.existsByAccountNumber(accountnumber);
    }

    public User createUserInfo(User new_user_info){
        return userRepository.save(new_user_info);
    }

    public Account createAccountInfo(Account new_account_info){
        return accountRepository.save(new_account_info);
    }

    public Fiance findByFianceId(String financeName){
        return fianceRepository.findByName(financeName).orElseThrow(() -> new RuntimeException("fianceName이 정확하지 않습니다."));
    }
}
