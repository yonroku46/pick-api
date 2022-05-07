package com.pick.controller;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;
import com.pick.entity.base.ListResponse;
import com.pick.entity.base.SingleResponse;
import com.pick.service.PublicService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PublicController {

    private final PublicService publicService;
    private final ResponseService responseService;

    /**
     * 유저 로그인
     */
    @PostMapping("/login")
    public SingleResponse<ResponseData> login(@RequestBody LoginReqDto req) {
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

    /**
     * 메일 중복확인 후 인증메일 발송
     */
    @PostMapping("/mailCheck")
    public SingleResponse<ResponseData> mailCheck(@RequestBody MailCheckReqDto req) {
        return responseService.getSingleResponse(publicService.mailCheck(req));
    }

    /**
     * 핀번호 인증확인
     */
    @PostMapping("/certification")
    public SingleResponse<ResponseData> certification(@RequestBody CertificationReqDto req) {
        return responseService.getSingleResponse(publicService.certification(req));
    }

    /**
     * 비밀번호 재설정
     */
    @PostMapping("/resetPwd")
    public SingleResponse<ResponseData> resetPwd(@RequestBody ResetPwdReqDto req) {
        return responseService.getSingleResponse(publicService.resetPwd(req));
    }

}
