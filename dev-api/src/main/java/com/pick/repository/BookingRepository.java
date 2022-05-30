package com.pick.repository;

import com.pick.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.sql.Timestamp;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value =
            "SELECT" +
            "  booking_cd,booking_category,booking_time,booking_price," +
            "  JSON_UNQUOTE(JSON_EXTRACT(booking_detail,'$.style')) as style," +
            "  JSON_UNQUOTE(JSON_EXTRACT(booking_detail,'$.designer')) as designer," +
            "  JSON_UNQUOTE(JSON_EXTRACT(booking_detail,'$.discount')) as discount," +
            "  JSON_UNQUOTE(JSON_EXTRACT(booking_detail,'$.customers')) as customers," +
            "  JSON_UNQUOTE(JSON_EXTRACT(booking_detail,'$.orders')) as orders," +
            "  mb.shop_cd,ms.shop_name" +
            " FROM" +
            "  public.m_booking mb INNER JOIN public.m_shop ms ON mb.shop_cd = ms.shop_cd" +
            " WHERE" +
            "  mb.booking_stat = 0" +
            " AND" +
            "  mb.user_cd = :user_cd"
            , nativeQuery = true)
    List<Tuple> bookingList(@Param("user_cd") String userCd);

    @Query(value =
            "SELECT COUNT(*) AS count" +
            " FROM public.m_booking" +
            " WHERE user_cd = :user_cd" +
            " AND booking_time = :booking_time"
            , nativeQuery = true)
    int bookingCheck(@Param("user_cd") Integer userCd, @Param("booking_time") Timestamp bookingTime);

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO public.m_booking" +
            "  (user_cd,shop_cd,booking_category,booking_time,booking_price," +
            "   booking_detail,create_time)" +
            " VALUES" +
            "  (:user_cd,:shop_cd,:booking_category,:booking_time,:booking_price," +
            "  JSON_OBJECT('designer',:designer,'style',:style,'discount',:discount),now())"
            , nativeQuery = true)
    int bookingHairshop(@Param("user_cd") Integer userCd, @Param("shop_cd") Integer shopCd, @Param("booking_category") String bookingCategory, @Param("booking_time") Timestamp bookingTime, @Param("booking_price") Integer bookingPrice,
                        @Param("designer") Integer designer, @Param("style") Integer style, @Param("discount") Integer discount);
    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO public.m_booking" +
            "  (user_cd,shop_cd,booking_category,booking_time,booking_price," +
            "  booking_detail,create_time)" +
            " VALUES" +
            "  (:user_cd,:shop_cd,:booking_category,:booking_time,:booking_price," +
            "  JSON_OBJECT('customers',:customers,'orders',:orders,'discount',:discount),now())"
            , nativeQuery = true)
    int bookingRestaurant(@Param("user_cd") Integer userCd, @Param("shop_cd") Integer shopCd, @Param("booking_category") String bookingCategory, @Param("booking_time") Timestamp bookingTime, @Param("booking_price") Integer bookingPrice,
                        @Param("customers") Integer customers, @Param("orders") String orders, @Param("discount") Integer discount);

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO public.m_booking" +
                    "  (user_cd,shop_cd,booking_category,booking_time,booking_price," +
                    "  booking_detail,create_time)" +
                    " VALUES" +
                    "  (:user_cd,:shop_cd,:booking_category,:booking_time,:booking_price," +
                    "  JSON_OBJECT('customers',:customers,'orders',:orders,'discount',:discount),now())"
            , nativeQuery = true)
    int bookingCafe(@Param("user_cd") Integer userCd, @Param("shop_cd") Integer shopCd, @Param("booking_category") String bookingCategory, @Param("booking_time") Timestamp bookingTime, @Param("booking_price") Integer bookingPrice,
                          @Param("customers") Integer customers, @Param("orders") String orders, @Param("discount") Integer discount);

    @Query(value =
            "SELECT" +
            "  booking_cd,booking_category,booking_time,booking_price," +
            "  JSON_UNQUOTE(JSON_EXTRACT(booking_detail,'$.style')) as style," +
            "  JSON_UNQUOTE(JSON_EXTRACT(booking_detail,'$.designer')) as designer," +
            "  JSON_UNQUOTE(JSON_EXTRACT(booking_detail,'$.discount')) as discount," +
            "  JSON_UNQUOTE(JSON_EXTRACT(booking_detail,'$.customers')) as customers," +
            "  JSON_UNQUOTE(JSON_EXTRACT(booking_detail,'$.orders')) as orders," +
            "  mb.shop_cd,ms.shop_name" +
            " FROM" +
            "  public.m_booking mb INNER JOIN public.m_shop ms ON mb.shop_cd = ms.shop_cd" +
            " WHERE" +
            "  mb.booking_stat = 0" +
            " AND" +
            "  mb.shop_cd = :shop_cd"
            , nativeQuery = true)
    List<Tuple> dashboardBookingList(@Param("shop_cd") Integer shopCd);

}
