package com.travel.board;

import com.travel.domain.Board;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  //페이징하여 보드 반환
  public Page<Board> findBoardList(Pageable pageable, String column) {
    Sort sort = Sort.by(column).descending();
    pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, 7,
        sort);
    return boardRepository.findAll(pageable);
  }
  //id로 보드 반환
  public Board findBoardByIdx(Integer bid) {
    return boardRepository.findById(bid).orElse(null);
  }

}