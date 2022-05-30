package com.travel.board;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

  @GetMapping("/list")
  String communityMapping() {
    return "board/board";
  }

  //게시판 작성페이지 이동
  @GetMapping("/write")
  public String communityWriteMapping() {
    return "board/board-write";
  }
}