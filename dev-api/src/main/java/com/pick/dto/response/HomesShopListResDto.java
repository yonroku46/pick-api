package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomesShopListResDto extends ResponseData {
    private Integer shopCd;
    private String category;
    private String shopName;
    private String shopLocation;
    private String shopInfo;
    private String shopTel;
    private String shopImg;
    private Float ratingsAve;
    private BigInteger reviewNum;

    public HomesShopListResDto(Tuple tuple) {
        this.shopCd = (Integer) tuple.get(0);
        this.category = convertSerial((String) tuple.get(1));
        this.shopName = (String) tuple.get(2);
        this.shopLocation = (String) tuple.get(3);
        this.shopInfo = (String) tuple.get(4);
        this.shopTel = (String) tuple.get(5);
        this.shopImg = (String) tuple.get(6);
        this.ratingsAve = (Float) tuple.get(7);
        this.reviewNum = (BigInteger) tuple.get(8);
    }
}
