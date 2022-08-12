package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeSaveReqDto {
    private String category;
    private String title;
    private String content;
    private Integer activeFlag;
}