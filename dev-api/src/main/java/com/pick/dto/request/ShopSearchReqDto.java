package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class ShopSearchReqDto {
    private String category;
    private String value;
}
