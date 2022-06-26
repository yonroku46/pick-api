package com.pick.repository;

import com.pick.entity.User;
import com.pick.model.ShopStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.sql.Timestamp;
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

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user" +
            " SET additional = JSON_MERGE(additional, JSON_OBJECT('info','','career',''))" +
            " WHERE user_cd = :user_cd"
            , nativeQuery = true)
    int updateEmployment(@Param("user_cd") Integer userCd);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user" +
            " SET additional = JSON_REMOVE(additional, '$.employment')" +
            " WHERE user_cd = :user_cd"
            , nativeQuery = true)
    int deleteEmployment(@Param("user_cd") Integer userCd);

    @Query(value =
            "SELECT count(*) AS count" +
            " FROM public.m_user" +
            " WHERE user_email = :user_email"
            , nativeQuery = true)
    int emailCount(@Param("user_email") String userEmail);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user" +
            " SET pin = :pin" +
            " WHERE user_email = :user_email"
            , nativeQuery = true)
    int updatePin(@Param("pin") String pin, @Param("user_email") String userEmail);

    @Query(value =
            "SELECT count(*) AS count" +
            " FROM public.m_user" +
            " WHERE user_email = :user_email AND pin = :pin"
            , nativeQuery = true)
    int pinCount(@Param("pin") String pin, @Param("user_email") String userEmail);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user" +
            " SET pin = NULL" +
            " WHERE user_email = :user_email"
            , nativeQuery = true)
    int pinReset(@Param("user_email") String userEmail);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user" +
            " SET user_pw = :user_pw" +
            " WHERE user_email = :user_email"
            , nativeQuery = true)
    int resetPwd(@Param("user_email") String userEmail, @Param("user_pw") String userPw);

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO public.m_user" +
            "  (user_email,role,pin)" +
            " VALUES" +
            "  (:user_email,:role,:pin)"
            , nativeQuery = true)
    int signupNormal(@Param("user_email") String userEmail, @Param("role") Integer role, @Param("pin") String pin);

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO public.m_user" +
            "  (user_email,role,pin,additional)" +
            " VALUES" +
            "  (:user_email,:role,:pin,'{}')"
            , nativeQuery = true)
    int signupStaff(@Param("user_email") String userEmail, @Param("role") Integer role, @Param("pin") String pin);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user" +
            " SET user_name = :user_name, user_pw = :user_pw" +
            " WHERE user_email = :user_email"
            , nativeQuery = true)
    int signup(@Param("user_email") String userEmail, @Param("user_name") String userName, @Param("user_pw") String userPw);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user" +
            " SET user_img = :img_path" +
            " WHERE user_cd = :user_cd"
            , nativeQuery = true)
    int userImgUpdate(@Param("img_path") String imgPath, @Param("user_cd") Integer userCd);

    User findByUserEmail(String userEmail);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user " +
            " SET access_time = :access_time " +
            " WHERE user_cd = :user_cd"
            , nativeQuery = true)
    void updateAccessTime(@Param("user_cd") Integer userCd, @Param("access_time") Timestamp accessTime);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user" +
            " SET additional = JSON_REPLACE(additional, '$.info', :#{#staff.info}, '$.career', :#{#staff.career})" +
            " WHERE user_cd = :#{#staff.userCd}"
            , nativeQuery = true)
    int updateStaffInfo(@Param("staff") ShopStaff staff);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_user" +
            " SET additional = JSON_REMOVE(additional, '$.info', '$.career', '$.employment')" +
            " WHERE user_cd = :#{#staff.userCd}"
            , nativeQuery = true)
    int deleteStaffInfo(@Param("staff") ShopStaff staff);

    @Query(value = "select u from User u where u.userCd = :userCd and u.deleteFlag = 0")
    User findUserByUserCd(@Param("userCd") Integer userCd);
}
