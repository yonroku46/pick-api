package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DashboardRequestConfirmReqDto {
    private Integer shopCd;
    private Integer userCd;
    private Integer requestCd;
    private Integer requestStat;
}
