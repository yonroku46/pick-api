package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopListResDto extends ResponseData {
    private Integer shopCd;
    private String shopName;
    private String shopLocation;
    private String shopInfo;
    private String shopTel;
    private String shopImg;
    private Float ratingsAve;

    public ShopListResDto(Tuple tuple) {
        this.shopCd = (Integer) tuple.get(0);
        this.shopName = (String) tuple.get(1);
        this.shopLocation = (String) tuple.get(2);
        this.shopInfo = (String) tuple.get(3);
        this.shopTel = (String) tuple.get(4);
        this.shopImg = (String) tuple.get(5);
        this.ratingsAve = (Float) tuple.get(6);
    }
}
