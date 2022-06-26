package com.pick.talk.Controller;

import com.pick.talk.dto.request.*;
import com.pick.talk.dto.response.TalkContentDto;
import com.pick.talk.dto.response.TalkContentsResDto;
import com.pick.talk.dto.response.TalkRoomDto;
import com.pick.talk.dto.response.TalkRoomsResDto;
import com.pick.talk.service.TalkService;
import com.pick.dto.response.BooleanResDto;
import com.pick.entity.base.SingleResponse;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public SingleResponse<TalkRoomsResDto> talkRoomList(TalkRoomListReqDto talkRoomListReqDto) {
        List<TalkRoomDto> talkRooms = talkService.findTalkRoomList(talkRoomListReqDto);
        return responseService.getSingleResponse(new TalkRoomsResDto(talkRooms));
    }

    // 채팅방 입장 -> 채팅내용 출력 -> TalkContentsResDto 리스트 리턴
    @GetMapping("/enter")
    public SingleResponse<TalkContentsResDto> enterTalkRoom(EnterTalkRoomReqDto enterTalkRoomReqDto) throws IllegalAccessException {
        List<TalkContentDto> messages = talkService.findMessages(enterTalkRoomReqDto);
        return responseService.getSingleResponse(new TalkContentsResDto(messages));
    }

    // 채팅방 새로 생성 -> talkRoomCd 리턴 (-> redirect하기)
    @GetMapping("/create")
    public SingleResponse<Integer> createTalkRoom(CreateTalkReqDto createTalkReqDto) {
        return responseService.getSingleResponse(talkService.createTalkRoom(createTalkReqDto));
    }

    // 메세지 보내기 -> Boolean 리턴
    @PostMapping("/send")
    public SingleResponse<BooleanResDto> sendMessage(@RequestBody SendMessageReqDto sendMessageReqDto) throws IllegalAccessException {
        return responseService.getSingleResponse(talkService.sendMessage(sendMessageReqDto));
    }

    // 대화방 나가기 -> Boolean 리턴 (-> 대화방 목록 페이지로 redirect)
    @GetMapping("/leave")
    public SingleResponse<BooleanResDto> leaveTalkRoom(LeaveTalkRoomReqDto leaveTalkRoomReqDto) throws IllegalAccessException {
        return responseService.getSingleResponse(talkService.leaveTalkRoom(leaveTalkRoomReqDto));
    }
}
