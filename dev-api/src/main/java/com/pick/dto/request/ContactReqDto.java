package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactReqDto {
    private String userName;
    private String userEmail;
    private String category;
    private String detail;
}
