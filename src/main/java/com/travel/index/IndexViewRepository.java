package com.travel.index;

import com.travel.domain.IdxView;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IndexViewRepository extends JpaRepository<IdxView, Long> {

    @Query(nativeQuery=true, value="select * from idx_view")
    List<IdxView> findAllView();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="update idx_view i SET i.slide_title=:slideTitleData Where i.view_idx=1")
    void updateSlideTitle(@Param("slideTitleData") String slideTitleData);
}
