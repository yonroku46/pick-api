package com.pick.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopInfo {
    private Integer shopCd;
    private String shopOpen;
    private String shopClose;
    private String shopHoliday;
    private List<ShopImg> shopImg;
    private String shopInfo;
    private String shopLocation;
    private String shopName;
    private String shopSerial;
    private String shopTel;
    private String staffCdList;
    private List<ShopStaff> staffList;
    private String category;
    private Float locationLat;
    private Float locationLng;
    private String menuCdList;
    private List<ShopMenu> menuList;
    private List<String> menuCategories;
    private BigInteger ratingsAve;
}
