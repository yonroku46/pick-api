package com.pick.repository;

import com.pick.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value =
            "SELECT user_cd,user_name,user_email,user_info,user_img,role," +
            " JSON_UNQUOTE(JSON_EXTRACT(additional,'$.employment')) AS employment" +
            " FROM public.m_user" +
            " WHERE user_email = :user_email" +
            " AND user_pw = :user_pw" +
            " AND delete_flag = 0"
            , nativeQuery = true)
    Tuple login(@Param("user_email") String userEmail, @Param("user_pw") String userPw);

    @Query(value =
            "SELECT shop_cd" +
            " FROM public.m_favorite" +
            " WHERE user_cd = :user_cd"
            , nativeQuery = true)
    List<Tuple> myFavorites(@Param("user_cd") Integer userCd);

    @Query(value =
            "SELECT user_cd,user_name,user_img," +
            " JSON_UNQUOTE(JSON_EXTRACT(additional,'$.career')) AS career," +
            " JSON_UNQUOTE(JSON_EXTRACT(additional,'$.info')) AS info" +
            " FROM public.m_user" +
            " WHERE user_cd IN :staff_cd_list"
            , nativeQuery = true)
    List<Tuple> getStaffList(@Param("staff_cd_list") String[] cdList);

    @Query(value =
            "SELECT" +
            "  msr.request_cd,msr.request_time,mu.user_cd,mu.user_name,mu.user_email,mu.user_img" +
            " FROM public.m_shop_request msr" +
            " INNER JOIN public.m_user mu" +
            " ON msr.user_cd = mu.user_cd" +
            " WHERE msr.shop_cd = :shop_cd AND request_stat = 0"
            , nativeQuery = true)
    List<Tuple> dashboardRequestList(@Param("shop_cd") Integer shopCd);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user SET user_info = :user_info WHERE user_cd = :user_cd"
            , nativeQuery = true)
    int userInfoUpdate(@Param("user_cd") Integer userCd, @Param("user_info") String userInfo);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user" +
            " SET additional = JSON_MERGE(additional, JSON_OBJECT('employment',:shop_cd))" +
            " WHERE user_cd = :user_cd"
            , nativeQuery = true)
    int submitEmployment(@Param("user_cd") Integer userCd, @Param("shop_cd") Integer shopCd);

    @Query(value =
            "SELECT * FROM public.m_user"
    , nativeQuery = true)
    List<User> searchAll();

}
