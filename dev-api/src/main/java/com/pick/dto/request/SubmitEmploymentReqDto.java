package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubmitEmploymentReqDto {
    private Integer userCd;
    private String shopSerial;
    private Integer role;
}
