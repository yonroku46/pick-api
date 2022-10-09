package com.pick.repository;

import com.pick.entity.Shop;
import com.pick.model.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    @Query(value =
            "SELECT shop_cd,shop_name,shop_location,shop_info,shop_tel,shop_img,ratings_ave" +
            " FROM public.m_shop" +
            " WHERE delete_flag = 0" +
            " AND shop_serial LIKE :category%"
            , nativeQuery = true)
    List<Tuple> shopList(@Param("category") String category);

    @Query(value =
            "SELECT shop_cd,shop_name,shop_location,shop_info,shop_tel,shop_img,ratings_ave" +
            " FROM (" +
            "  SELECT * FROM public.m_shop WHERE shop_name LIKE %:value% UNION" +
            "  SELECT * FROM public.m_shop WHERE shop_location LIKE %:value% UNION" +
            "  SELECT * FROM public.m_shop WHERE shop_info LIKE %:value%" +
            " ) sub1" +
            " WHERE sub1.delete_flag = 0" +
            " AND sub1.shop_serial LIKE :category%"
            , nativeQuery = true)
    List<Tuple> search(@Param("category") String category, @Param("value") String value);

    @Query(value =
            "SELECT" +
            "  shop_cd,shop_name,shop_location,shop_info,shop_tel,shop_img,shop_open,shop_close,shop_break_start,shop_break_end,shop_holiday,staff_list,menu_list,ratings_ave,shop_serial,location_lat,location_lng," +
            "  (SELECT COUNT(*) FROM m_shop_review WHERE shop_cd = :shop_cd AND delete_flag = 0 AND review_reply IS NULL) AS review_num," +
            "  (SELECT COUNT(*) FROM m_favorite WHERE shop_cd = :shop_cd) AS favorite_num" +
            " FROM" +
            "  public.m_shop" +
            " WHERE" +
            "  delete_flag = 0" +
            " AND" +
            "  shop_cd = :shop_cd"
            , nativeQuery = true)
    Tuple shopInfo(@Param("shop_cd") Integer shopCd);

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO public.m_favorite (user_cd,shop_cd) VALUES (:user_cd,:shop_cd)"
            , nativeQuery = true)
    int insFavorite(@Param("user_cd") Integer userCd, @Param("shop_cd") Integer shopCd);

    @Modifying
    @Transactional
    @Query(value =
            "DELETE FROM public.m_favorite WHERE user_cd = :user_cd AND shop_cd = :shop_cd"
            , nativeQuery = true)
    int delFavorite(@Param("user_cd") Integer userCd, @Param("shop_cd") Integer shopCd);

    @Query(value =
            "SELECT" +
            "  shop_cd,shop_name,shop_location,shop_info,shop_tel,shop_img,shop_open,shop_close,shop_break_start,shop_break_end,shop_holiday,staff_list,menu_list,shop_serial,location_lat,location_lng" +
            " FROM" +
            "  public.m_shop" +
            " WHERE" +
            "  delete_flag = 0" +
            " AND" +
            "  shop_cd = :shop_cd"
            , nativeQuery = true)
    Tuple dashboardInfo(@Param("shop_cd") Integer shopCd);

    @Query(value =
            "SELECT shop_cd" +
            " FROM public.m_shop" +
            " WHERE shop_serial = :shop_serial"
            , nativeQuery = true)
    Integer getBySerial(@Param("shop_serial") String shopSerial);

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO public.m_shop_request (shop_cd,user_cd,request_time)" +
            " VALUES (:shop_cd,:user_cd,now())"
            , nativeQuery = true)
    int submitEmployment(@Param("user_cd") Integer userCd, @Param("shop_cd") Integer shopCd);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_shop" +
            " SET staff_list = CONCAT(staff_list,',',:user_cd)" +
            " WHERE shop_cd = :shop_cd"
            , nativeQuery = true)
    int addStaff(@Param("user_cd") Integer userCd, @Param("shop_cd") Integer shopCd);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_shop" +
            " SET" +
            "  shop_location = :#{#shop.shopLocation}, shop_info = :#{#shop.shopInfo}, shop_tel = :#{#shop.shopTel}," +
            "  shop_img = :shop_img, shop_open = :#{#shop.shopOpen}, shop_close = :#{#shop.shopClose}," +
            "  shop_break_start = :#{#shop.shopBreakStart}, shop_break_end = :#{#shop.shopBreakEnd}," +
            "  shop_holiday = :#{#shop.shopHoliday}, location_lat = :#{#shop.locationLat}, location_lng = :#{#shop.locationLng}," +
            "  staff_list = :staff_list, menu_list = :menu_list" +
            " WHERE shop_cd = :#{#shop.shopCd}"
            , nativeQuery = true)
    int saveInfo(@Param("shop") ShopInfo shop, @Param("shop_img") String shopImg, @Param("staff_list") String staffSet,  @Param("menu_list") String menuSet);

    @Query(value =
            "SELECT *," +
            "  (SELECT count(*) FROM public.m_shop_review WHERE shop_cd = sub1.shop_cd AND delete_flag = 0 AND review_reply IS NULL) AS review_num" +
            " FROM (SELECT shop_cd,shop_serial,shop_name,shop_location,shop_info,shop_tel,shop_img,ratings_ave FROM public.m_shop WHERE delete_flag = 0) sub1" +
            " LIMIT 10"
            , nativeQuery = true)
    List<Tuple> getEventShopList();

    @Query(value =
            "SELECT *," +
            "  (SELECT count(*) FROM public.m_shop_review WHERE shop_cd = sub1.shop_cd AND delete_flag = 0 AND review_reply IS NULL) AS review_num" +
            " FROM (SELECT shop_cd,shop_serial,shop_name,shop_location,shop_info,shop_tel,shop_img,ratings_ave FROM public.m_shop WHERE delete_flag = 0) sub1" +
            " WHERE sub1.shop_location LIKE :location%" +
            " LIMIT 10"
            , nativeQuery = true)
    List<Tuple> getNearShopList(String location);

    @Query(value =
            "SELECT shop_manager" +
            " FROM public.m_shop" +
            " WHERE shop_cd = :shop_cd"
            , nativeQuery = true)
    Integer getShopManager(@Param("shop_cd") Integer shopCd);

}
