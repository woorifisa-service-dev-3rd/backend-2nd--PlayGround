package playground.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import playground.dto.*;
import playground.model.Account;
import playground.model.Fiance;
import playground.model.TheCheat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import playground.model.User;
import playground.service.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/recent/{id}")
    public List<AccountRecentDTO> accountRecent(@PathVariable Long id){
        List<Account> byAllAccount = accountService.findByOrderByDateTimeDesc(id);
        List<AccountRecentDTO> list = new ArrayList<>();
        for(Account account : byAllAccount) {
            list.add(AccountRecentDTO.builder().bankCode(account.getFiance().getId()).userName(account.getUser().getUserName()).accountNumber(account.getAccountNumber()).uid(account.getId()).build());
        }
        return list;
    }

    @ResponseBody
    @GetMapping("/often/{id}")
    public List<AccountOftenDTO> accountOften(@PathVariable Long id){
        List<Object[]> objects = accountService.findAccountOftenDTO(id);
        return objects.stream().map((object)->AccountOftenDTO.builder()
                        .bankCode(((Number)object[0]).longValue())
                        .accountNumber(String.valueOf(object[1]))
                        .uid(((Number)object[2]).longValue())
                        .userName(String.valueOf(object[3]))
                        .build())
                .collect(Collectors.toList());
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

    @GetMapping("/insert")
    public String getAccount_info(){
        //insert.jsp
        return "add";
    }

    @PostMapping("/insert")
    public String insertAccount_info(@ModelAttribute UserInsertDTO userInsertDTO) {
        // Convert DTO to User and Fiance
        System.out.println("userInsertDTO = " + userInsertDTO);
        User user = User.from(userInsertDTO);
        System.out.println("user = " + user);
//        Fiance fiance = Fiance.from(userInsertDTO);
        Fiance fiance = accountService.findByFianceId(userInsertDTO.getFinance_name());
        System.out.println("fiance = " + fiance);
        Account savedAccount;
        // Check if the user exists
        if (accountService.isUserinfo(userInsertDTO.getName())) {
            // Fetch the existing user from the database
            User existingUser = accountService.findUser(userInsertDTO.getName());

            // Save the Fiance entity
            //Fiance savedFiance = accountService.createFianceInfo(fiance);

            // Create Account with the existing User and saved Fiance entity
            Account account = Account.from(userInsertDTO, existingUser, fiance);

            // Save the Account
            savedAccount = accountService.createAccountInfo(account);

        } else {
            // If the user doesn't exist, create and save the new User
            User savedUser = accountService.createUserInfo(user);

            // Save the Fiance entity
            //Fiance savedFiance = accountService.createFianceInfo(fiance);

            // Now, create Account with the saved User and Fiance entity
            Account account = Account.from(userInsertDTO, savedUser, fiance);

            // Save the Account
            savedAccount = accountService.createAccountInfo(account);
        }


        return "redirect:/account";
    }

}
