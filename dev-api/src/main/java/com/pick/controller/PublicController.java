package com.pick.controller;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.BookingCheckReqDto;
import com.pick.dto.request.LoginReqDto;
import com.pick.dto.request.MyFavoritesReqDto;
import com.pick.dto.response.LoginResDto;
import com.pick.entity.base.ListResponse;
import com.pick.entity.base.SingleResponse;
import com.pick.service.PublicService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Tuple;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PublicController {

    private final PublicService publicService;
    private final ResponseService responseService;

    /**
     * 유저 로그인
     */
    @GetMapping("/login")
    public SingleResponse<ResponseData> login(LoginReqDto req) {
        return responseService.getSingleResponse(publicService.login(req));
    }

    /**
     * 해당 유저의 즐겨찾기 매장코드 리스트
     */
    @GetMapping("/myFavorites")
    public ListResponse<ResponseData> myFavorites(MyFavoritesReqDto req) {
        return responseService.getListResponse(publicService.myFavorites(req));
    }

    /**
     * 해당 유저의 예약시간 중복체크
     */
    @GetMapping("/bookingCheck")
    public SingleResponse<ResponseData> bookingCheck(BookingCheckReqDto req) {
        return responseService.getSingleResponse(publicService.bookingCheck(req));
    }

}
