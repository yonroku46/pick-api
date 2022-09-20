package com.pick.dto.request;

import com.pick.model.BookingDetail;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class BookingReqDto {
    private Integer userCd;
    private Integer shopCd;
    private Timestamp bookingTime;
    private Timestamp bookingEndTime;
    private Integer bookingPrice;
    private String category;
    private BookingDetail bookingDetail;
}
