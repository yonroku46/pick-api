package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupReqDto {
    private String userEmail;
    private String userName;
    private String userPw;
}
