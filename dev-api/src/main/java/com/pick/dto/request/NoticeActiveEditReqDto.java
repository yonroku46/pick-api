package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeActiveEditReqDto {
    private Integer noticeCd;
    private Integer activeFlag;
}