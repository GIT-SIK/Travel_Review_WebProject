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
  public Page<Board> findBoardList(Pageable pageable, String column, String param) {

    Sort sort = Sort.by(column).descending();
    pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
        10,
        sort);
    if (param.equals("서울")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("경기")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("인천")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("강원")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("세종")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("대전")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("충북")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("충남")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("대구")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("부산")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("울산")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("경북")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("경남")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("광주")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("전북")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("전남")) {
      return boardRepository.findByCategory(param, pageable);
    } else if (param.equals("제주")) {
      return boardRepository.findByCategory(param, pageable);
    }
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


  /* 내 정보 : ROLE로 파악하여 관리자 전용 보드, 유저 전용 보드 반환*/
  public Page<Board> findBoardById( String id, String role, Pageable pageable) {
    String column = "idx";
    Sort sort = Sort.by(column).descending();
    pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
            10,
            sort);
    System.out.println(role);
    if(role.equals("ROLE_ADMIN")) {
      return boardRepository.findAllBoard(pageable);
    } else {
      return boardRepository.findByUserId(id, pageable);
    }
  }



  /* 따봉 올리기 */
  @Transactional
  public int updateRecommend(int id) {
    return boardRepository.updateRecommend(id);
  }

  @Transactional
  @Modifying
  public void viewRecommendUpdate(Integer idx, HttpServletRequest request,
      HttpServletResponse response, String param) {
    boolean cookieHas = false;
//        param = "boardView or boardRecommend"
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        String name = cookie.getName();
        String value = cookie.getValue();
        if (param.equals(name) && value.contains("|" + idx + "|")) {
          cookieHas = true;
          break;
        }
      }
    }

    if (!cookieHas) {
      Cookie cookie = new Cookie(param, param + "|" + idx + "|");
      if (param.equals("boardView")) {
        cookie.setMaxAge(-1);
        //브라우저 끄면 쿠기 사라지고 조회수 증가 가능
        response.addCookie(cookie);
        this.updateView(idx);
      } else if (param.equals("boardRecommend")) {
        cookie.setMaxAge(60 * 60 * 24 * 365);
        //브라우저 꺼도 쿠키 안사라지고 1년동안 보관.
        // 쿠키를 지우지 않고선 1년 동안 추천수 조작 불가
        response.addCookie(cookie);
        this.updateRecommend(idx);
      }
    }
  }
}