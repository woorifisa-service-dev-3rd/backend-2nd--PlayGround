package playground.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import playground.dto.TransferDTO;
import playground.dto.TransferRequest;

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
        boolean isExist = accountRepository.existsByAccountNumber(accountNumber);
        if(!isExist){
            return "없는 계좌번호 입니다.";
        }
        List<TheCheat> theCheatsAccountNumber = theCheatRepository.findByAccount_AccountNumber(accountNumber);

        if(theCheatsAccountNumber.isEmpty()) return "정상계좌 입니다.";
        return "사기꾼 계좌 입니다.";
    }

    public boolean searchAccountApi(String accountNumber){
        boolean isExist = accountRepository.existsByAccountNumber(accountNumber);
        if(!isExist){
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
    public TransferDTO findResult(Long userId) {
        Account account = accountRepository.findByUserIdOrderByDateTimeDesc(userId, PageRequest.of(0, 1)).get(0);
        TransferDTO transferDTO = TransferDTO.builder().money(account.getMoney()).depositAndWithdrawalMoney(account.getDepositAndWithdrawalMoney()).accountNumber(account.getAccountNumber()).description(account.getDescription()).fianceId(account.getFiance().getId()).fianceName(account.getFiance().getName()).build();
        return transferDTO;
    }

    public boolean TransferResultSave(TransferRequest transferRequest, Long userId) {
        String accountNumber = transferRequest.getAccount();
        Long depositAndWithdrawalMoney = transferRequest.getAmount();
        String memo = transferRequest.getMemo();
        Long fianceId = transferRequest.getBank();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("userId가 맞지 않습니다"));
        Fiance fiance = fianceRepository.findById(fianceId).orElseThrow(() -> new RuntimeException("fianceId가 맞지 않습니다."));
        Account account = accountRepository.findByAccountNumberOrderByDateTimeDesc(accountNumber, PageRequest.of(0,1)).get(0);
        if(account.getFiance().getId() != fiance.getId()) throw new RuntimeException("은행과 계좌번호가 맞지 않습니다.");
        if(account.getMoney() - depositAndWithdrawalMoney <0) throw new RuntimeException("현재 금액보다 입금하려는 금액이 더 많습니다.");
        Account createAccount = Account.builder().accountNumber(accountNumber).user(user).fiance(fiance).depositAndWithdrawalMoney(depositAndWithdrawalMoney).description(memo).money(account.getMoney() - depositAndWithdrawalMoney).build();
        Account savedAccount = accountRepository.save(createAccount);
        if(savedAccount != null) return true;
        return false;
    }
}
