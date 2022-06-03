package com.travel.festival;

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
}