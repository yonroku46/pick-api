package com.pick.repository;

import com.pick.dto.response.LoginResDto;
import com.pick.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value =
            "SELECT user_cd,user_name,user_email,user_info,user_img,permission" +
            " FROM public.m_user" +
            " WHERE user_email = :user_email" +
            " AND user_pw = :user_pw" +
            " AND delete_flag = 0"
    , nativeQuery = true)
    Tuple login(@Param("user_email") String userEmail, @Param("user_pw") String userPw);

    @Query(value =
            "SELECT * FROM public.m_user"
    , nativeQuery = true)
    List<User> searchAll();

}
