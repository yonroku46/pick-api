package com.pick.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPostReqDto {
    private Boolean isStaff;
    private Integer userCd;
    private Integer shopCd;
    private Integer reviewReply;
    private String reviewText;
    private Integer ratings;
}
