package playground.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import playground.dto.AccountRecentDTO;
import playground.dto.CheatResult;
import playground.dto.Info;
import playground.dto.cheatResponse;
import playground.model.Account;
import playground.model.TheCheat;
import playground.service.AccountService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public String searchAccountForm(){
        return "index";
    }

    @PostMapping
    public String searchAccount(@RequestParam String accountNumber, Model model){
        String str = accountService.searchAccount(accountNumber);
        model.addAttribute("str",str);
        return "index";
    }

    @ResponseBody
    @GetMapping({"/recent", "/often"})
    public List<AccountRecentDTO> accountRecent(){
        List<Account> byAllAccount = accountService.findByAllAccount();
        List<AccountRecentDTO> list = new ArrayList<>();
        for(Account account : byAllAccount) {
            list.add(AccountRecentDTO.builder().bankCode(account.getFiance().getId()).userName(account.getUser().getUserName()).accountNumber(account.getAccountNumber()).uid(account.getId()).build());
        }
        return list;
    }

    @ResponseBody
    @PostMapping("/cheat")
    public CheatResult cheatResult(@RequestBody cheatResponse cheatResponse){
        System.out.println("account = " + cheatResponse);
        boolean flag = accountService.searchAccountApi(cheatResponse.getAccount());
        List<TheCheat> byTheCheatLog = accountService.findByTheCheatLog(cheatResponse.getAccount());
        Long amount = 0L;
        for(TheCheat theCheat : byTheCheatLog){
            amount += theCheat.getAmount();
        }
        Info info = Info.builder().cases(byTheCheatLog.size()).amount(amount).build();
        return CheatResult.builder().result(flag).info(info).build();
    }


}
