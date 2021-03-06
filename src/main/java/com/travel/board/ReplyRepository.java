package com.travel.board;

import com.travel.domain.Reply;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Query("select r from Reply r join fetch r.board where r.rBid=:idx" )
    List<Reply> findAllByBid(@Param(value="idx") Integer idx);

    Page<Reply> findAllByrAuthor(@Param(value="rAuthor") String userId, Pageable pageable);
}
