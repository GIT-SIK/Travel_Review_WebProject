package com.travel.board;

import com.travel.domain.Board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {

  private final BoardService boardService;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping("/list")
  String communityMapping(Model model,Pageable pageable) {
    Page<Board> p = boardService.findBoardList(pageable, "idx");
    int totalPage = p.getTotalPages();
    model.addAttribute("boardList", p);
    model.addAttribute("totalPage", totalPage);
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
    String redirect = "redirect:/board/list";
    return redirect;
  }

  //게시판 삭제
  @PostMapping("/delete")
  public @ResponseBody Boolean communityDeleteMapping(@RequestBody JSONObject jsonObject) {
    Integer idx = (Integer)jsonObject.get("idx");
    boardService.deleteBoard(idx);
    return true;
  }

  //게시판 기본 조회
  @GetMapping("/details")
  public String boardDetails(@RequestParam(value = "idx", defaultValue = "0") Integer idx, Model model, HttpServletRequest request, HttpServletResponse response) {
    boardService.viewUpdate(idx, request, response);
    Board board = boardService.findBoardByIdx(idx);
    model.addAttribute("idx", idx);
    model.addAttribute("board", board);
    return "/board/board-details";
  }
  //게시판 추천하기
  @GetMapping("/recommend")
  public String boardDetailsRecommend(@RequestParam(value = "idx", defaultValue = "0") Integer idx, Model model, HttpServletRequest request, HttpServletResponse response) {
    boardService.recommendUpdate(idx, request, response);
    Board board = boardService.findBoardByIdx(idx);
    model.addAttribute("idx", idx);
    model.addAttribute("board", board);
    return "/board/board-details";
  }


}