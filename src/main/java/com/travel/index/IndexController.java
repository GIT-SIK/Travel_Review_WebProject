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

}
