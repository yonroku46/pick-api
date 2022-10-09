package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import com.pick.model.ShopMenu;
import com.pick.model.ShopStaff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class ShopInfoResDto extends ResponseData {
    private Integer shopCd;
    private String shopName;
    private String shopLocation;
    private String shopInfo;
    private String shopTel;
    private String shopImg;
    private String shopOpen;
    private String shopClose;
    private String shopBreakStart;
    private String shopBreakEnd;
    private String shopHoliday;
    private List<String> shopHolidayList;
    private String staffCdList;
    private List<ShopStaff> staffList;
    private String menuCdList;
    private List<ShopMenu> menuList;
    private List<String> menuCategories;
    private Float ratingsAve;
    private String shopSerial;
    private String category;
    private Double locationLat;
    private Double locationLng;
    private BigInteger reviewNum;
    private BigInteger favoriteNum;
    private Map<String,String> bookingFlags;
    private Map<String,List<String>> bookingTimes;

    public ShopInfoResDto(Tuple tuple) {
        this.shopCd = (Integer) tuple.get(0);
        this.shopName = (String) tuple.get(1);
        this.shopLocation = (String) tuple.get(2);
        this.shopInfo = (String) tuple.get(3);
        this.shopTel = (String) tuple.get(4);
        this.shopImg = (String) tuple.get(5);
        this.shopOpen = (String) tuple.get(6);
        this.shopClose = (String) tuple.get(7);
        this.shopBreakStart = (String) tuple.get(8);
        this.shopBreakEnd  = (String) tuple.get(9);
        this.shopHoliday = (String) tuple.get(10);
        this.shopHolidayList = null;
        this.staffCdList = (String) tuple.get(11);
        this.staffList = null;
        this.menuCdList = (String) tuple.get(12);
        this.menuList = null;
        this.menuCategories = null;
        this.ratingsAve = (Float) tuple.get(13);
        this.shopSerial = (String) tuple.get(14);
        this.category = convertSerial(this.shopSerial);
        this.locationLat = (Double) tuple.get(15);
        this.locationLng = (Double) tuple.get(16);
        this.reviewNum = (BigInteger) tuple.get(17);
        this.favoriteNum = (BigInteger) tuple.get(18);
        this.bookingFlags = null;
        this.bookingTimes = null;
    }

}
