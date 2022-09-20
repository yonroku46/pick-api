package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class BookingCheckReqDto {
    private Integer userCd;
    private Timestamp bookingTime;
    private Timestamp bookingEndTime;
}
