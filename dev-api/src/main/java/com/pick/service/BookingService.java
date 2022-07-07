package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.BookingInfoReqDto;
import com.pick.dto.request.BookingListReqDto;
import com.pick.dto.request.BookingReqDto;
import com.pick.dto.request.DashboardBookingListReqDto;

import java.util.List;

public interface BookingService {

    public List<ResponseData> bookingList(BookingListReqDto req);
    public ResponseData booking(BookingReqDto req);
    public List<ResponseData> dashboardBookingList(DashboardBookingListReqDto req);
    public ResponseData bookingInfo(BookingInfoReqDto req);

}
