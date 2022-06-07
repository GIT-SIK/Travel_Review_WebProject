package com.travel.user;

import com.travel.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByTel(String tel);

    User findById(String id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="update user u SET u.password=:password Where u.id=:id")
    int updateUser(@Param("id") String id, @Param("password") String password);

    /* 관리자에서 유저테이블 가지고 오는 쿼리문 */
    @Query("SELECT u FROM User u ORDER BY u.id")
    Page<User> findAllUser(Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM user u Where u.id=:id")
    int deleteUser(@Param("id") String id);
}
