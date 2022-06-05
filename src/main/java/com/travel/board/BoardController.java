package com.travel.board;

import com.travel.domain.Board;
import com.travel.security.auth.UserDetails;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {

  private final BoardService boardService;
  private final ReplyService replyService;

  public BoardController(BoardService boardService, ReplyService replyService) {
    this.boardService = boardService;
    this.replyService = replyService;
  }

  @GetMapping("/list/{param}")
  String communityMapping(Model model,Pageable pageable, @PathVariable String param) {

    Page<Board> p = null;
    if (param.equals("main")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("서울")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("경기")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("인천")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("강원")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("세종")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("대전")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("충북")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("충남")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("대구")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("부산")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("울산")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("경북")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("경남")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("광주")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("전북")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("전남")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }else if(param.equals("제주")) {
      p = boardService.findBoardList(pageable, "idx", param);
    }
    int totalPage = p.getTotalPages();
    int nowPage = p.getPageable().getPageNumber()+1;
    int startPage = Math.max(nowPage-4, 1);
    int endPage = Math.min(nowPage+4, p.getTotalPages());

    model.addAttribute("boardList", p);
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("nowPage", nowPage);
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    model.addAttribute("checkParam", param);

    return "board/board";
  }

  //후기 작성페이지 이동
  @GetMapping("/write")
  public String communityWriteMapping() {
    return "board/board-write";
  }

  //게시판 작성
  @ResponseBody
  @PostMapping("/create")
  public Board boardCreate(@RequestBody Board board) {
    boardService.boardCreate(board);
    return board;
  }

  //게시판 수정페이지 이동
  @GetMapping("/rewrite")
  public String communityReWriteMapping(@RequestParam(value = "idx", defaultValue = "0") Integer idx, Model model) {
    model.addAttribute("board", boardService.findBoardByIdx(idx));
    return "board/board-rewrite";
  }
  //게시판 수정
  @PostMapping("/rewrite")
  public String communityPostReWriteMapping(@ModelAttribute("board") Board board) {
    boardService.updateBoard(board);
    String redirect = "redirect:/board/list/main";
    return redirect;
  }

  //게시판 삭제
  @PostMapping("/delete")
  public @ResponseBody Boolean communityDeleteMapping(@RequestBody JSONObject jsonObject) {
    Integer idx = (Integer)jsonObject.get("idx");
    boardService.deleteBoard(idx);
    return true;
  }

  @PostMapping("/userDelete")
  @ResponseBody
  public boolean userCommunityDeleteMapping(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("idx") String idx, @RequestParam("userId") String id) {

    boolean check = false;
    try {
      if (userDetails.getUser().getId().equals(id)) {
        int idxTemp = Integer.parseInt(idx);
        boardService.deleteBoard(idxTemp);
        check = true;
      }
    } catch (Exception e){
      System.out.println("삭제도중 오류발생");
    }

    return check;
  }

  //게시판 기본 조회, 추천하기
  @GetMapping("/details/{param}")
  public String boardDetails(@RequestParam(value = "idx", defaultValue = "0") Integer idx, @PathVariable String param, Model model, HttpServletRequest request, HttpServletResponse response) {
    boardService.viewRecommendUpdate(idx, request, response, param);

    Board board = boardService.findBoardByIdx(idx);
    List<ReplyDto> replyList = replyService.getReplyList(idx);
    int countAllReply = replyService.countAllReply(idx);

    model.addAttribute("idx", idx);
    model.addAttribute("board", board);
    model.addAttribute("replyList", replyList);
    model.addAttribute("countAllReply", countAllReply);

    return "/board/board-details";
  }




  @PostMapping("/reply")
  @ResponseBody
  public String reply(@AuthenticationPrincipal UserDetails customUserDetails, @RequestBody Map<String, String> map) {
    if(map.get("text").equals("")){
      return "fail";
    }
    replyService.saveReply(map, customUserDetails);
    return "success";
  }



}