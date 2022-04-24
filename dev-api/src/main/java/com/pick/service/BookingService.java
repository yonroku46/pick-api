package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.BookingListReqDto;
import com.pick.entity.Booking;
import com.pick.entity.base.ListResponse;

import java.util.List;

public interface BookingService {

    public List<ResponseData> bookingList(BookingListReqDto req);
    public List<Booking> searchAll();

}
