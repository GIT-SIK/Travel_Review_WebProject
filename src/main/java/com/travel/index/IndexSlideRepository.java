package com.travel.index;

import com.travel.domain.IdxSlide;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IndexSlideRepository extends JpaRepository<IdxSlide, Integer> {


    @Query(nativeQuery=true, value="select * from idx_slide")
    List<IdxSlide> findAllSlide();


}
