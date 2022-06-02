package com.travel.board;

import com.travel.domain.Board;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

  private final BoardRepository boardRepository;


  public BoardService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  //보드 생성
  @Transactional
  public void boardCreate(Board board) {
    board.setTimeNow();
    boardRepository.save(board);
  }

  //보드 갱신
  @Transactional
  public void updateBoard(Board board) {
    boardRepository.save(board);
  }


  //보드 삭제
  public void deleteBoard(Integer idx) {
    boardRepository.deleteById(idx);
  }

  //페이징하여 보드 반환
  public Page<Board> findBoardList(Pageable pageable, String column) {
    Sort sort = Sort.by(column).descending();
    pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, 10,
        sort);
    return boardRepository.findAll(pageable);
  }

  //id로 보드 반환
  public Board findBoardByIdx(Integer idx) {
    return boardRepository.findById(idx).orElse(null);
  }

  /*  */
  @Transactional
  public int updateView(int id) {
    return boardRepository.updateView(id);
  }

  /* 따봉 올리기 */
  @Transactional
  public int updateRecommend(int id) {
    return boardRepository.updateRecommend(id);
  }

  @Transactional
  @Modifying
  public void viewUpdate(Integer idx, HttpServletRequest request,
      HttpServletResponse response) {
    boolean cookieHas = false;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        String name = cookie.getName();
        String value = cookie.getValue();
        if ("boardView".equals(name) && value.contains("|" + idx + "|")) {
          cookieHas = true;
          break;
        }
      }
    }

    if (!cookieHas) {
      Cookie cookie = new Cookie("boardView", "boardView|" + idx + "|");
      cookie.setMaxAge(-1);
      response.addCookie(cookie);
      this.updateView(idx);
    }
  }

  @Transactional
  @Modifying
  public void recommendUpdate(Integer idx, HttpServletRequest request,
      HttpServletResponse response) {
    boolean cookieHas = false;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        String name = cookie.getName();
        String value = cookie.getValue();
        if ("boardView".equals(name) && value.contains("|" + idx + "|")) {
          cookieHas = true;
          break;
        }
      }
    }

    if (!cookieHas) {
      Cookie cookie = new Cookie("boardView", "boardView|" + idx + "|");
      cookie.setMaxAge(-1);
      response.addCookie(cookie);
      this.updateRecommend(idx);
    }
  }
}