package com.travel.security;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class securityController {
    @GetMapping("/autherror")
    public String errorMapping() {
        return "error/error-page";
    }
}
