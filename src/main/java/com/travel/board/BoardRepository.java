package com.travel.board;

import com.travel.domain.Board;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

  @Modifying
  @Query("update Board a set a.view = a.view + 1 where a.idx = :id")
  int updateView(@Param("id") int id);

  @Modifying
  @Query("update Board a set a.recommend = a.recommend + 1 where a.idx = :id")
  int updateRecommend(@Param("id") int id);

  @Query("SELECT a FROM Board a where a.category = :category ORDER BY a.idx")
  Page<Board> findByCategory(@Param("category") String category, Pageable pageable);


  /* 유저의 내정보 작성된 게시판 쿼리문 */
  @Query("SELECT a FROM Board a where a.userId = :userId ORDER BY a.idx")
  Page<Board> findByUserId(@Param("userId") String id, Pageable pageable);


  /* 관리자의 내정보 작성된 전체게시판 쿼리문 */
  @Query("SELECT a FROM Board a ORDER BY a.idx")
  Page<Board> findAllBoard(Pageable pageable);

  @Query(nativeQuery = true, value="SELECT * FROM board where content LIKE '%<img%' ORDER BY recommend DESC LIMIT 3 ")
  List<Board> findRecommend();

  @Query(value = "SELECT DISTINCT * FROM board a WHERE a.content LIKE %:keyword% OR a.title LIKE %:keyword% ORDER BY a.recommend DESC LIMIT 10", nativeQuery = true)
  List<Board> findByContentContaining(@Param("keyword") String keyword);

  @Query(value = "SELECT * FROM board a WHERE a.category LIKE %:keyword% ORDER BY a.recommend DESC LIMIT 10", nativeQuery = true)
  List<Board> findByLocalContaining(@Param("keyword") String keyword);
}
