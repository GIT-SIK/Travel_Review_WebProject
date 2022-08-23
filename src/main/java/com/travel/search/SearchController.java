package com.travel.search;

import com.travel.domain.Board;
import com.travel.domain.Festival;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<Board> boardLocalList = searchService.searchBoardLocal(keyword);
        List<Board> boardContentList = searchService.searchBoardContent(keyword);
        List<Festival> festivalLocalList = searchService.searchFestivalLocal(keyword);
        List<Festival> festivalNameList = searchService.searchFestivalName(keyword);

        model.addAttribute("boardLocalList", boardLocalList);
        model.addAttribute("boardContentList", boardContentList);
        model.addAttribute("festivalLocalList", festivalLocalList);
        model.addAttribute("festivalNameList", festivalNameList);
        model.addAttribute("keyword", keyword);
        return "search/search-result";
    }
}
