package com.travel.index;

import com.travel.domain.idxSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexSlideRepository extends JpaRepository<idxSlide, Long> {


    @Query(nativeQuery=true, value="select * from idx_slide")
    List<idxSlide> findAllSlide();
}
