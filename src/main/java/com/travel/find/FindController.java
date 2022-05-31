package com.travel.find;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/find")
public class FindController {

    private final FindService findService;

    @GetMapping("/findId")
    public String findId() {
        return "view/find/find-id";
    }

    @PostMapping("/findIdByTel")
    public String findIdByTel(@RequestParam("tel") String tel, Model model) {
        String message = findService.findIdByTel(tel);
        model.addAttribute("message", message);
        return "view/find/find-id-result";
    }

    @PostMapping("/findIdByEmail")
    public String findIdByEmail(@RequestParam("email") String email, Model model) {
        String message = findService.findIdByEmail(email);
        model.addAttribute("message", message);
        return "view/find/find-id-result";
    }

    @GetMapping("/findPw")
    public String findPwAuth() {
        return "view/find/find-pw";
    }
}
