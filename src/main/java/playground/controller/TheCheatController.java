package playground.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import playground.dto.CheatCheckResponse;
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
    public String showCheatForm(CheatCheckResponse cheatCheckResponse, Model model)
    {
        model.addAttribute("cheatCheckResponse",cheatCheckResponse);
        return "insert";
    }

    @PostMapping
    public String createTheCheats(@RequestParam String accountNumber,
                                  @RequestParam String description,
                                  @RequestParam Long amount,
                                  RedirectAttributes rttr,
                                  CheatCheckResponse cheatCheckResponse) {

        Account findaccount = theCheatService.findAccount(accountNumber);
        System.out.println("findaccount = " + findaccount);
        if(findaccount != null) {
            theCheatService.createOrUpdateTheCheat(findaccount, accountNumber,description, amount);
            rttr.addFlashAttribute("str", "더치트에 추가되었습니다.");
        }
        else {
            rttr.addFlashAttribute("str", "없는 계좌입니다.");
        }
        rttr.addFlashAttribute("cheatCheckResponse",cheatCheckResponse);

        return "redirect:/cheat";
    }

}