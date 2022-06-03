package com.travel.index;

import com.travel.domain.TbIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface IndexRepository extends JpaRepository<TbIndex, Long> {

    @Query(nativeQuery=true, value="select * from tb_index")
    List<TbIndex> findAuth();

}
