package com.travel.index;


import com.travel.domain.Festival;
import com.travel.domain.IdxSlide;
import com.travel.domain.IdxView;
import com.travel.security.auth.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping({"", "/"})
    public String index(Model model) {

        List<IdxSlide> slideLinkList = indexService.findAllSlide();
        List<IdxView> viewList = indexService.findAllView();
        model.addAttribute("SlideLinkList", slideLinkList);
        model.addAttribute("viewList", viewList);
        return "index";
    }


    @PostMapping("/index/festival")
    @ResponseBody
    public List<Festival> indexFestival(@RequestParam(value = "Local") String local) {
        return indexService.findAllbyDataLocal(local);
    }

    /* 메인페이지 축제 월을 리턴해줌 / Ajax로 사용됨 */
    @GetMapping("/index/viewdate")
    @ResponseBody
    public String indexViewDate() {
        return indexService.findDate();
    }

    /* 관리자 페이지에서 슬라이드 추가 & 삭제를 함. */
    @PostMapping("/index/slideDelete")
    @ResponseBody
    public boolean indexSildeData(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("idx") String idx) {
        return indexService.deleteSlide(userDetails.getUser().getRole(), idx);
    }

    @PostMapping("/index/slideAdd")
    public String indexSildeAdd(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("slideAddLk") String slideLink, @RequestParam("slideAddTt") String slideTitle, @RequestParam("slideAddCt") String slideCentent, @RequestParam("slideAddPs") String slidePosition){

        if(userDetails.getUser().getRole().equals("ROLE_ADMIN")) {
            indexService.addSlide(userDetails.getUser().getRole(), slideLink, slideTitle, slideCentent, slidePosition);
            return "redirect:/user/admin";
        } else {
            return "redirect:/login";
        }

    }
}
