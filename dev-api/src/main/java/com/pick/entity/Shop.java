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

    @Column(name = "shop_break_start")
    private String shopBreakStart;

    @Column(name = "shop_break_end")
    private String shopBreakEnd;

    @Column(name = "shop_holiday")
    private String shopHoliday;

    @Column(name = "ratings_ave", columnDefinition = "integer default 0")
    private Double ratingsAve;

    @Column(name = "location_lat", columnDefinition = "integer default 0")
    private Double locationLat;

    @Column(name = "location_lng", columnDefinition = "integer default 0")
    private Double locationLng;

    @Column(name = "delete_flag", columnDefinition = "integer default 0")
    private Integer deleteFlag;

    @PrePersist
    public void prePersist(){
        this.ratingsAve = this.ratingsAve == null ? 0: this.ratingsAve;
        this.locationLat = this.locationLat == null ? 0: this.locationLat;
        this.locationLng = this.locationLng == null ? 0: this.locationLng;
        this.deleteFlag = this.deleteFlag == null ? 0: this.deleteFlag;
    }

}