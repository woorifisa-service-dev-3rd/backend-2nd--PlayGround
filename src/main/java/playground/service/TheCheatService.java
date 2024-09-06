package playground.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import playground.model.Account;
import playground.model.TheCheat;
import playground.repository.AccountRepository;
import playground.repository.TheCheatRepository;

@Service
public class TheCheatService {

    @Autowired
    private TheCheatRepository theCheatRepository;
    @Autowired
    private AccountRepository accountRepository;


    public TheCheat createOrUpdateTheCheat(Long accountNumber, String description) {
        //여기 수정
//        Account account = accountRepository.findById(accountNumber)
//                .orElseThrow(() -> new RuntimeException("Account not found"));

        TheCheat theCheat = TheCheat.builder()
                .accountNumber(accountNumber)
                .description(description)
                .build();

        return theCheatRepository.save(theCheat);
    }

//    public String saveAccount(String account) {
//        TheCheat cheater = new TheCheat();
//        cheater.setAccount(account);
//        return
//    }
}
