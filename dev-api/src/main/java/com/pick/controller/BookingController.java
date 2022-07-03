package com.pick.controller;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.BookingInfoReqDto;
import com.pick.entity.base.SingleResponse;
import com.pick.service.BookingService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;
    private final ResponseService responseService;

    /**
     * 해당 예약의 상세정보
     */
    @GetMapping("/info")
    public SingleResponse<ResponseData> bookingInfo(BookingInfoReqDto req) {
        return responseService.getSingleResponse(bookingService.bookingInfo(req));
    }
}
