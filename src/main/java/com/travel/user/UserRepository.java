package com.travel.user;

import com.travel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.id=:id")
    User findById(@Param("id") String id);

    User findByEmail(String email);

    User findByTel(String tel);

    User findById(String id);

}
