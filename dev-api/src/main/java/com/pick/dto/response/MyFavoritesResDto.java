package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class MyFavoritesResDto extends ResponseData {
    private Integer shopCd;

    public MyFavoritesResDto(Tuple tuple) {
        this.shopCd = (Integer) tuple.get(0);
    }
}
