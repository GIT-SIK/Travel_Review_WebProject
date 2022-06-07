package com.travel.index;


import com.travel.board.BoardService;
import com.travel.domain.Board;
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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class IndexController {

    @Autowired
    IndexService indexService;

    @Autowired
    BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model) {

        List<Board> bestBoardList = boardService.findBoardBest();

        for( int i =0 ;i< bestBoardList.size(); i++)
        {
            Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식
            Matcher matcher = pattern.matcher(bestBoardList.get(i).getContent());

            while(matcher.find()){
                bestBoardList.get(i).setContent(matcher.group(1));
            }
        }

        List<IdxSlide> slideLinkList = indexService.findAllSlide();
        List<IdxView> viewList = indexService.findAllView();

        model.addAttribute("bestBoardList", bestBoardList);
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
