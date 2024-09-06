package playground.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import playground.model.Account;
import playground.model.TheCheat;
import playground.repository.AccountRepository;
import playground.repository.TheCheatRepository;
import playground.service.AccountService;
import playground.service.TheCheatService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cheat")
public class TheCheatController {
    private final TheCheatService theCheatService;

    @GetMapping
    public String showCheatForm() {
        return "insert";
    }

    @PostMapping
    public RedirectView createTheCheats(@RequestParam String accountNumber, @RequestParam String description, @RequestParam Long amount) {
        Account findaccount = theCheatService.findAccount(accountNumber);
        if(findaccount != null) theCheatService.createOrUpdateTheCheat(findaccount, accountNumber,description, amount);
        else throw new RuntimeException("존재하지 않는 계좌입니다.");

        return new RedirectView("/cheat");
    }

}