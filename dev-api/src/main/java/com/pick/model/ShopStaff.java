package com.pick.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopStaff {
    private Integer userCd;
    private String userName;
    private String userImg;
    private String career;
    private String info;

    public ShopStaff(Tuple tuple) {
        this.userCd = (Integer) tuple.get(0);
        this.userName = (String) tuple.get(1);
        this.userImg = (String) tuple.get(2);
        this.career = (String) tuple.get(3);
        this.info = (String) tuple.get(4);
    }
}
