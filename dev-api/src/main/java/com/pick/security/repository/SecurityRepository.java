package com.pick.security.repository;

import com.pick.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

public interface SecurityRepository extends JpaRepository<User, Integer> {
    User findByUserEmail(String userEmail);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.accessTime = :accessTime where u.userCd = :userCd")
    void updateAccessTime(@Param("userCd") Integer userCd,
                          @Param("accessTime") Timestamp accessTime);
}
