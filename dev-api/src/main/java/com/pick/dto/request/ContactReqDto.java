package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactReqDto {
    private String name;
    private String email;
    private String category;
    private String detail;
}
