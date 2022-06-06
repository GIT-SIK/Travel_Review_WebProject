package com.travel.user;


import com.travel.board.BoardService;
import com.travel.domain.Board;
import com.travel.domain.IdxView;
import com.travel.domain.User;
import com.travel.index.IndexService;
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
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    private final IndexService indexService;
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

    /* &&&&******************************** 내 정보 / 관리자 영역  ********************************&&&& */


    /* *************************************  데이터 처리 ************************************* */
    @GetMapping(value = {"/user/admin", "/user/mypage"})
    public String adminDataMapping(@AuthenticationPrincipal UserDetails userDetails, Model model, Pageable pageable) {

        /* ** 공통 영역 ** */
        Page<Board> p = boardDataSubMapping(userDetails.getUser().getId(), userDetails.getUser().getRole(), pageable);
        int totalPage = p.getTotalPages();
        int nowPage = p.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage-4, 1);
        int endPage = Math.min(nowPage+4, p.getTotalPages());

        model.addAttribute("boardList", p);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        /* ** 관리자 영역 ** */
        if(userDetails.getUser().getRole().equals("ROLE_ADMIN")){

            List<IdxView> viewList = indexService.findAllView();
            IdxView idxView = viewList.get(0);
            model.addAttribute("idxSlideList", indexService.findAllSlide());
            model.addAttribute("idxViewList", idxView);

            return "user/admin";
        } else {

        /* ** 유저 영역 ** */

            /* 유저 정보 리턴 */
            model.addAttribute("userInfo", userService.infoUser(userDetails));
            return "user/mypage";
        }

    }


    public Page<Board> boardDataSubMapping(String id, String role, Pageable pageable){
        Page<Board> p = boardService.findBoardById(id ,role ,pageable);
        return p;
    }
    /* *************************************  관리자 영역 ************************************* */
    /* 관리자 페이지에서 슬라이드 삭제함. */
    @PostMapping("/user/admin/slideDelete")
    @ResponseBody
    public boolean indexSildeData(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("idx") String idx) {
        return indexService.deleteSlide(userDetails.getUser().getRole(), idx);
    }


    /* 관리자 페이지에서 슬라이드 추가함. */
    @PostMapping("/user/admin/slideAdd")
    public String indexSildeAdd(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("slideAddLk") String slideLink, @RequestParam("slideAddTt") String slideTitle, @RequestParam("slideAddCt") String slideCentent, @RequestParam("slideAddPs") String slidePosition){

            indexService.addSlide(userDetails.getUser().getRole(), slideLink, slideTitle, slideCentent, slidePosition);
            return "redirect:/user/admin";


    }

    /* 관리자 페이지에서 슬라이드 타이틀을 변경함. */
    @PostMapping("/user/admin/slideTitleUpdate")
    public String indexSlideTitleUpdate(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("slideTitleData") String slideTitleData){

        indexService.updateSlideTitle(userDetails.getUser().getRole(), slideTitleData);
        return "redirect:/user/admin";


    }

    /* 관리자 페이지에서 축제출력 날짜 월을 변경함. (연도는 현재 기준으로 한다.) */
    @PostMapping("/user/admin/indexFestivalDate")
    public String indexFestivalDateUpdate(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("indexFestivalMonth") String festivalMonth){

        indexService.updateIndexFestivalDate(userDetails.getUser().getRole(), festivalMonth);
        return "redirect:/user/admin";


    }

    /* *************************************  유저 영역 ************************************* */
    /* 유저 탈퇴 */
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

    /* 유저 업데이트 / 비밀번호만 연결 */
    @PostMapping("/user/mypage/update")
    public String updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("password") String password) {
        userService.updateUser(userDetails.getUser().getId() ,bCryptPasswordEncoder.encode(password));
        return "redirect:/user/mypage";
    }

    /* 유저 마이페이지 / 관리자 페이지 에서 게시물 삭제 */ /* 관리자도 포함되어 있는 Controller 입니다. */
    @PostMapping("/user/mypage/userBoardDelete")
    @ResponseBody
    public boolean userCommunityDeleteMapping(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("idx") String idx, @RequestParam("userId") String id) {
        boolean check = false;
        try {
            if (userDetails.getUser().getId().equals(id) || userDetails.getUser().getRole().equals("ROLE_ADMIN")) {
                int idxTemp = Integer.parseInt(idx);
                boardService.deleteBoard(idxTemp);
                check = true;
            }
        } catch (Exception e){
            System.out.println("삭제도중 오류발생");

        }

        return check;
    }
}
