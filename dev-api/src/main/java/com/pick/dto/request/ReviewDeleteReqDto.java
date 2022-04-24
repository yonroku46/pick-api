package com.pick.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDeleteReqDto {
    private Integer userCd;
    private Integer shopCd;
    private Integer reviewCd;
}
