package com.travel.festival;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/festival")
public class FestivalController {

    @GetMapping("/list")
    public String festival() {
        return "festival/festival";
    }
}
