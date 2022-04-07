package com.pick.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "m_user_coupon")
public class UserCoupon {

    @Id
    @Column(name = "coupon_cd")
    private Integer couponCd;

    @Column(name = "user_cd")
    private Integer userCd;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "coupon_discount")
    private Integer couponDiscount;

    @Column(name = "coupon_end")
    private Timestamp couponEnd;
}