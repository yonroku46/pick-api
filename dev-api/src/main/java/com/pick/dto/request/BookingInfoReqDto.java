package com.pick.dto.request;

import com.pick.model.BookingDetail;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class BookingInfoReqDto {
    private Integer userCd;
    private Integer bookingCd;
}
