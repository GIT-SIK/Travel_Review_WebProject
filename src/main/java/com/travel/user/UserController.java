package com.travel.user;


import com.travel.board.BoardService;
import com.travel.domain.Board;
import com.travel.domain.User;
import com.travel.security.auth.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final BoardService boardService;


    
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

    /* &&&&******************************** 내 정보 영역  ********************************&&&& */


    /* ************************************* 관리자 ************************************* */

    @GetMapping("/user/admin")
    String adminMapping(){
        return "user/admin";
    }


    /* ************************************* 일반유저 ************************************* */
    @GetMapping("/user/mypage")
    String mypageMapping(@AuthenticationPrincipal UserDetails userDetails, Model model, Pageable pageable){

        /* 유저 기본 정보 리턴 */
        String id = userDetails.getUsername();
        model.addAttribute("userInfo", userService.infoUser(userDetails));

        /* 유저 게시판 정보 리턴 */
        Page<Board> p = boardService.findBoardById(userDetails.getUser().getId() ,pageable);
        int totalPage = p.getTotalPages();
        int nowPage = p.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage-4, 1);
        int endPage = Math.min(nowPage+4, p.getTotalPages());

        model.addAttribute("boardList", p);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


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

    @PostMapping("/user/mypage/update")
    public String updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("password") String password) {
        int updateCheck = userService.updateUser(userDetails.getUser().getId() ,bCryptPasswordEncoder.encode(password));
        return "redirect:/user/mypage";
    }
    
}
