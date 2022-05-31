package com.travel.find;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/find")
public class FindController {

    private final FindService findService;
    private final MailService mailService;

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

    @PostMapping("/emailGetCode")
    @ResponseBody
    public String emailGetCode(@RequestParam("id") String id, @RequestParam("email") String email, Model model) {
        if (findService.checkEmail(id, email) == 1) {
            String code = mailService.sendEmailMessage(email);
            model.addAttribute("code", code);
            return code;
        } else {
            return "회원정보가 일치하지 않습니다.";
        }
    }

    @PostMapping("/telGetCode")
    @ResponseBody
    public String telGetCode(@RequestParam("id") String id, @RequestParam("tel") String tel, Model model) {
        if (findService.checkTel(id, tel) == 1) {
            String code = mailService.sendTelMessage(tel);
            model.addAttribute("code", code);
            return code;
        } else {
            return "회원정보가 일치하지 않습니다.";
        }
    }

    @PostMapping("/findPwByEmail")
    public String findPwByEmail(@RequestParam("code") String code, Model model) {
        String result = mailService.getUserIdByEmailCode(code);
        if (result.equals("fail")) {
            model.addAttribute("message", "인증코드를 다시 한 번 확인해주세요.");
            return ("view/find/find-pw-fail");
        } else {
            model.addAttribute("userId", result);
            return ("view/find/find-pw-success");
        }
    }

    @PostMapping("/findPwByTel")
    public String findPwByTel(@RequestParam("code") String code, Model model) {
        String result = mailService.getUserIdByTelCode(code);
        if (result.equals("fail")) {
            model.addAttribute("message", "인증코드를 다시 한 번 확인해주세요.");
            return ("view/find/find-pw-fail");
        } else {
            model.addAttribute("userId", result);
            return ("view/find/find-pw-success");
        }
    }

    @PostMapping("/changePw")
    public String changePw(@RequestParam("id") String id, @RequestParam("password") String password) {
//        password = bCryptPasswordEncoder.encode(password);
        findService.changePassword(id, password);
        return "redirect:/";
    }
}
