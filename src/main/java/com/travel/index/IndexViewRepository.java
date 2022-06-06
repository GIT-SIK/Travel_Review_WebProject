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

    /* 관리자에서 슬라이드 타이틀 명, 화면 보이기 감추기 등 세팅값 반환 */
    @Query(nativeQuery=true, value="select * from idx_view")
    List<IdxView> findAllView();

    /* 메인 슬라이드 타이틀 명 업데이트 */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="update idx_view i SET i.slide_title=:slideTitleData Where i.view_idx=1")
    void updateSlideTitle(@Param("slideTitleData") String slideTitleData);

    /* 메인 축제 출력 날짜 업데이트(월 선택) */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="update idx_view i SET i.festival_date=:festivalMonth Where i.view_idx=1")
    void updateIndexFestivalMonth(@Param("festivalMonth") String festivalMonth);

    /* 메인 축제 출력 날짜 업데이트(자동) */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="update idx_view i SET i.festival_date=(curdate()) Where i.view_idx=1")
    void updateIndexFestivalMonthAuto();
}
