package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeEditReqDto {
    private Integer noticeCd;
    private String category;
    private String title;
    private String content;
    private Integer activeFlag;
}