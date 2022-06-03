package com.travel.festival;

import com.travel.domain.Festival;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/festival")
public class FestivalController {

    private final FestivalService festivalService;

    @GetMapping("/list")
    public String festival(Model model) {
        // 초기 날짜에 맞는 데이터 넣어서 반환하기
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        List<Festival> festivalList = festivalService.festivalData(today);
        model.addAttribute("festivalList", festivalList);
        System.out.println("festivalList = " + festivalList);
        return "festival/festival";
    }

//    @PostMapping("/monthSelect")
//    @ResponseBody
//    public List<Festival> changeMonth(@RequestParam("month") int month) {
//
//    }
}
