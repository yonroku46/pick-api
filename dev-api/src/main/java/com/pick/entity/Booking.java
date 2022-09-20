package com.pick.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "m_booking")
public class Booking {

    @Id
    @Column(name = "booking_cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingCd;

    @Column(name = "user_cd")
    private Integer userCd;

    @Column(name = "shop_cd")
    private Integer shopCd;

    @Column(name = "booking_category")
    private String bookingCategory;

    @Column(name = "booking_time")
    private Timestamp bookingTime;

    @Column(name = "booking_end_time")
    private Timestamp bookingEndTime;

    @Column(name = "booking_detail", columnDefinition = "json")
    @JsonRawValue
    private String bookingDetail;

    @Column(name = "booking_price")
    private Integer bookingPrice;

    @Column(name = "booking_stat")
    private Integer bookingStat;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

}