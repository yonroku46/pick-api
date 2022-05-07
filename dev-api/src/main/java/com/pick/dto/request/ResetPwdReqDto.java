package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetPwdReqDto {
    private String userEmail;
    private String userPw;
}
