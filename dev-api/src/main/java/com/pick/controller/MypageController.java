package com.pick.controller;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;
import com.pick.entity.base.ListResponse;
import com.pick.entity.base.SingleResponse;
import com.pick.service.BookingService;
import com.pick.service.FavoriteService;
import com.pick.service.UserService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MypageController {

    private final UserService userService;
    private final BookingService bookingService;
    private final ResponseService responseService;
    private final FavoriteService favoriteService;

    /**
     * 해당 유저의 예약 리스트
     */
    @GetMapping("/bookingList")
    public ListResponse<ResponseData> bookingList(BookingListReqDto req) {
        return responseService.getListResponse(bookingService.bookingList(req));
    }

    /**
     * 해당 유저의 즐겨찾기 리스트
     */
    @GetMapping("/favoriteList")
    public ListResponse<ResponseData> favoriteList(FavoriteListReqDto req) {
        return responseService.getListResponse(favoriteService.favoriteList(req));
    }

    /**
     * 해당 유저 소개글 수정
     */
    @PostMapping("/infoUpdate")
    public SingleResponse<ResponseData> userInfoUpdate(@RequestBody UserInfoUpdateReqDto req) {
        return responseService.getSingleResponse(userService.userInfoUpdate(req));
    }

    /**
     * 해당 유저 소속신청
     */
    @PostMapping("/submit")
    public SingleResponse<ResponseData> submitEmployment(@RequestBody SubmitEmploymentReqDto req) {
        return responseService.getSingleResponse(userService.submitEmployment(req));
    }
}
