package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FavoriteReqDto {
    private Integer userCd;
    private Integer shopCd;
    private Boolean isFavorite;
}
