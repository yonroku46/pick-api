package com.pick.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopMenu {
    private Integer menuCd;
    private String menuCategory;
    private String menuName;
    private String menuDescription;
    private Integer menuPrice;
    private String menuImg;

    public ShopMenu(Tuple tuple) {
        this.menuCd = (Integer) tuple.get(0);
        this.menuCategory = (String) tuple.get(1);
        this.menuName = (String) tuple.get(2);
        this.menuDescription = (String) tuple.get(3);
        this.menuPrice = (Integer) tuple.get(4);
        this.menuImg = (String) tuple.get(5);
    }
}
