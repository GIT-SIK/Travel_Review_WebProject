package com.travel.festival;

import com.travel.domain.Board;
import com.travel.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Integer> {

    @Query(value = "select * from festival f where DATE_FORMAT(f.start_date, '%Y-%m') <= :date " +
            "and DATE_FORMAT(f.end_date, '%Y-%m') >= :date ", nativeQuery = true)
    List<Festival> findByDate(@Param("date") String date);

    @Query(value = "select * from festival f where (DATE_FORMAT(f.start_date, '%Y-%m') <= :date and DATE_FORMAT(f.end_date, '%Y-%m') >= :date) and f.road_address LIKE %:local%", nativeQuery = true)
    List<Festival> findALLByDateLocal(@Param("date") String date, @Param("local") String local);

    @Query(value = "SELECT * FROM festival a WHERE a.name LIKE %:keyword% ORDER BY a.start_date DESC", nativeQuery = true)
    List<Festival> findByNameContaining(@Param("keyword") String keyword);

    @Query(value = "SELECT distinct * FROM festival a WHERE a.road_address LIKE %:keyword% OR a.lot_num_address LIKE %:keyword% ORDER BY a.start_date DESC", nativeQuery = true)
    List<Festival> findByLocalContaining(@Param("keyword") String keyword);
}