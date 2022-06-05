package com.travel.user;

import com.travel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByTel(String tel);

    User findById(String id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="update user u SET u.password=:password Where u.id=:id")
    int updateUser(@Param("id") String id, @Param("password") String password);

}
