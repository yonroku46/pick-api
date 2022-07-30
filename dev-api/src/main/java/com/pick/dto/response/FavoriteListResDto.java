package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteListResDto extends ResponseData {
    private Integer shopCd;
    private String shopSerial;
    private String shopName;
    private String shopLocation;
    private String shopImg;
    private String category;

    public FavoriteListResDto(Tuple tuple) {
        this.shopCd = (Integer) tuple.get(0);
        this.shopSerial = (String) tuple.get(1);
        this.shopName = (String) tuple.get(2);
        this.shopLocation = (String) tuple.get(3);
        this.shopImg = (String) tuple.get(4);
        this.category = convertSerial(this.shopSerial);
    }
}
