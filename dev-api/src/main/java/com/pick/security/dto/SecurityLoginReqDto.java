package com.pick.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SecurityLoginReqDto {
    public String userEmail;
    public String userPw;
}
