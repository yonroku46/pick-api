package com.pick.controller;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;
import com.pick.dto.response.BooleanResDto;
import com.pick.entity.base.SingleResponse;
import com.pick.service.base.ResponseService;
import com.pick.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/***
 * page는 프론트단에서 1부터, 백단에서 0부터 시작
 */
@RestController
@RequestMapping("/api/talk")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class TalkController {
    private final TalkService talkService;
    private final ResponseService responseService;

    // 채팅방 목록 -> TalkRoomResDto 리턴
    @GetMapping("/roomList")
    public SingleResponse<ResponseData> talkRoomList(@Validated TalkRoomListReqDto req) {
        return responseService.getSingleResponse(talkService.talkRoomList(req));
    }

    // 채팅방 입장 -> 채팅내용 출력 -> TalkContentsResDto 리스트 리턴
    @GetMapping("/enter")
    public SingleResponse<ResponseData> enterTalkRoom(@Validated EnterTalkRoomReqDto req) throws IllegalAccessException {
        return responseService.getSingleResponse(talkService.enterTalkRoom(req));
    }

    // 채팅방 새로 생성 -> talkRoomCd 리턴 (-> redirect하기)
    @GetMapping("/create")
    public SingleResponse<ResponseData> createTalkRoom(@Validated CreateTalkReqDto req) {
        return responseService.getSingleResponse(talkService.createTalkRoom(req));
    }

    // 메세지 보내기 -> Boolean 리턴
    @PostMapping("/send")
    public SingleResponse<BooleanResDto> sendMessage(@Validated @RequestBody SendMessageReqDto req) throws IllegalAccessException {
        return responseService.getSingleResponse(talkService.sendMessage(req));
    }

    // 대화방 나가기 -> Boolean 리턴 (-> 대화방 목록 페이지로 redirect)
    @GetMapping("/leave")
    public SingleResponse<BooleanResDto> leaveTalkRoom(@Validated LeaveTalkRoomReqDto req) throws IllegalAccessException {
        return responseService.getSingleResponse(talkService.leaveTalkRoom(req));
    }
}
