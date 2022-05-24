package com.pick.controller;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;
import com.pick.entity.base.ListResponse;
import com.pick.entity.base.SingleResponse;
import com.pick.service.BookingService;
import com.pick.service.FavoriteService;
import com.pick.service.ShopService;
import com.pick.service.UserService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final UserService userService;
    private final BookingService bookingService;
    private final ShopService shopService;
    private final ResponseService responseService;

    /**
     * 해당 매장의 대쉬보드 정보
     */
    @GetMapping("/info")
    public SingleResponse<ResponseData> dashboardInfo(DashboardInfoReqDto req) {
        return responseService.getSingleResponse(shopService.dashboardInfo(req));
    }

    /**
     * 해당 매장의 스태프 리퀘스트 리스트
     */
    @GetMapping("/requestList")
    public ListResponse<ResponseData> dashboardRequestList(DashboardRequestListReqDto req) {
        return responseService.getListResponse(userService.dashboardRequestList(req));
    }

    /**
     * 해당 매장의 스태프 리퀘스트 승인/거절
     */
    @PostMapping("/requestConfirm")
    public SingleResponse<ResponseData> dashboardRequestConfirm(@RequestBody DashboardRequestConfirmReqDto req) {
        return responseService.getSingleResponse(userService.dashboardRequestConfirm(req));
    }

    /**
     * 해당 매장의 스태프 리퀘스트 승인/거절
     */
    @GetMapping("/bookingList")
    public ListResponse<ResponseData> dashboardBookingList(DashboardBookingListReqDto req) {
        return responseService.getListResponse(bookingService.dashboardBookingList(req));
    }

    /**
     * 해당 매장의 변경사항 저장
     */
    @PostMapping("/saveInfo")
    public SingleResponse<ResponseData> saveInfo(@RequestBody DashboardSaveInfoReqDto req) {
        return responseService.getSingleResponse(shopService.saveInfo(req));
    }

}
