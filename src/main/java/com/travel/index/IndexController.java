package com.travel.index;


import com.travel.domain.Festival;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping({"","/"})
    public String index(){
        return "index";
    }


    @PostMapping("/index/festival")
    @ResponseBody
    public List<Festival> indexFestival(@RequestParam(value = "Local") String local){
        return indexService.findAllbyDataLocal(local);
    }

    @GetMapping("/index/date")
    @ResponseBody
    public String indexDate(){
        return indexService.findDate();
    }
}
