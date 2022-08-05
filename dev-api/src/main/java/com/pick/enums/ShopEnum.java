package com.pick.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShopEnum {
    HAIRSHOP("hairshop", "HS"),
    RESTAURANT("restaurant", "HS"),
    CAFE("cafe", "HS")
    ;

    private String type;
    private String code;

}