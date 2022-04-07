package com.pick.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "m_shop_request")
public class ShopRequest {

    @Id
    @Column(name = "request_cd")
    private Integer requestCd;

    @Column(name = "shop_cd")
    private Integer shopCd;

    @Column(name = "user_cd")
    private Integer userCd;

    @Column(name = "request_time")
    private Timestamp requestTime;

    @Column(name = "request_stat")
    private Integer requestStat;

}