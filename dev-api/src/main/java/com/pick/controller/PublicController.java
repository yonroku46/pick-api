package com.pick.controller;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.LoginReqDto;
import com.pick.dto.response.LoginResDto;
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
     * @return
     */
    @GetMapping("/login")
    public SingleResponse<ResponseData> login(LoginReqDto req) {
        return responseService.getSingleResponse(publicService.login(req));
    }

}
