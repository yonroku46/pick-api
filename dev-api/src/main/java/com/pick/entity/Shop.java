package com.pick.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "m_shop")
public class Shop {

    @Id
    @Column(name = "shop_cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopCd;

    @Column(name = "shop_serial")
    private String shopSerial;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_location")
    private String shopLocation;

    @Column(name = "shop_info")
    private String shopInfo;

    @Column(name = "shop_tel")
    private String shopTel;

    @Column(name = "shop_img")
    private String shopImg;

    @Column(name = "staff_list")
    private String staffList;

    @Column(name = "menu_list")
    private String menuList;

    @Column(name = "shop_open")
    private String shopOpen;

    @Column(name = "shop_close")
    private String shopClose;

    @Column(name = "shop_holiday")
    private String shopHoliday;

    @Column(name = "ratings_ave")
    private Double ratingsAve;

    @Column(name = "location_lat")
    private Double locationLat;

    @Column(name = "location_lng")
    private Double locationLng;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

}