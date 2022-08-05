package com.pick.controller;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.ContactReqDto;
import com.pick.entity.base.SingleResponse;
import com.pick.service.ContactService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;
    private final ResponseService responseService;

    /**
     * 문의사항 저장
     */
    @PostMapping("/save")
    public SingleResponse<ResponseData> contact(@RequestBody ContactReqDto req) {
        return responseService.getSingleResponse(contactService.contact(req));
    }

}
