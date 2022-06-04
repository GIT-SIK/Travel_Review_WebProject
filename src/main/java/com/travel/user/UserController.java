package com.travel.user;


import com.travel.domain.User;
import com.travel.security.auth.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;


    
    /* ************************************* 회원가입  ************************************* */

    /* 회원가입 페이지 매핑 */
    @GetMapping("/signup")
    public String signupMapping() {
        return "user/signup";
    }

    
    /* 회원가입 영역 */
    @PostMapping("/signup/data")
    public String signupData(@RequestParam("id") String id, @RequestParam("password") String pw, @RequestParam("email") String email, @RequestParam("tel") String tel) {

         User user = new User();

        try{
            user.setId(id);
            user.setEmail(email);
            user.setTel(tel);
            user.setRole("ROLE_USER");
            String rawPassword = pw;
            String enPassword = bCryptPasswordEncoder.encode(rawPassword);
            user.setPassword(enPassword);
            userService.signupUser(user);

            return "user/signup-success";

        } catch (DataIntegrityViolationException e) {
            return "user/signup-fail";
        }

    }

    /* 중복된 아이디 */
    @PostMapping("/signup/isId")
    @ResponseBody
    public boolean isId(@RequestBody String id){
        boolean check = false;
        if(id!=null)
        {
            check = userService.isId(id);
        }
        return check;

    }

    /* ************************************* 회원가입 끝 ************************************* */

    /* ************************************* 로그인 ************************************* */
        @GetMapping("/login")
        public String loginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "exception", required = false) String exception, Model model) {
            if(error!=null)
            model.addAttribute("error", error);
            if(exception!=null)
            model.addAttribute("exception", exception);

            return "user/login";
        }

    /* ************************************* 로그인 끝************************************* */
    /* ************************************* 관리자 ************************************* */

    @GetMapping("/user/admin")
    String adminMapping(){
        return "user/admin";
    }


    /* ************************************* 일반유저 ************************************* */
    @GetMapping("/user/mypage")
    String mypageMapping(){
        return "user/mypage";
    }

    @GetMapping("/user/mypage/delete")
    public String deleteUser(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request) {
        userService.deleteUser(userDetails.getUser());

        // 삭제가 완료할 경우 세션 끊기
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
    
}
