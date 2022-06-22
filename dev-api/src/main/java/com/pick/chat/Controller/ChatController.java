package com.pick.chat.Controller;

import com.pick.chat.dto.ChatContentsResDto;
import com.pick.chat.dto.ChatRoomResDto;
import com.pick.chat.service.ChatService;
import com.pick.entity.base.ListResponse;
import com.pick.entity.base.SingleResponse;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/***
 * page는 프론트단에서 1부터, 백단에서 0부터 시작
 */
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final ResponseService responseService;

    // 채팅방 목록 -> ChatRoomResDto 리턴
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/rooms")
    public ListResponse<ChatRoomResDto> chatRooms(@RequestParam(required = false, defaultValue = "1") Integer page) {
        return responseService.getListResponse(chatService.findChatRooms(page));
    }

    // 채팅내용 출력 -> ChatContentsResDto 리스트 리턴
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{chatRoomCd}")
    public ListResponse<ChatContentsResDto> enterChatRoom(@PathVariable Integer chatRoomCd,
                                                          @RequestParam(required = false, defaultValue = "1") Integer page) throws IllegalAccessException {
        return responseService.getListResponse(chatService.findMessages(chatRoomCd, page));
    }

    // 채팅방 새로 생성 -> chatRoomCd 리턴 -> redirect하기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/{toUserCd}")
    public SingleResponse<Integer> startChat(@PathVariable Integer toUserCd) {
        return responseService.getSingleResponse(chatService.startChat(toUserCd));
    }

    // 메세지 보내기 -> Boolean 리턴
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{chatRoomCd}/send")
    public SingleResponse<Boolean> sendMessage(@PathVariable Integer chatRoomCd,
                                               @RequestBody HashMap<String, String> messageMap) throws IllegalAccessException {
        chatService.sendMessage(chatRoomCd, messageMap.get("message"));
        return responseService.getSingleResponse(true);
    }

    // 대화방 나가기 -> 대화방 목록 페이지로 redirect
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{chatRoomCd}/leave")
    public SingleResponse<Boolean> leaveChatRoom(@PathVariable Integer chatRoomCd) throws IllegalAccessException {
        chatService.leaveChatRoom(chatRoomCd);
        return responseService.getSingleResponse(true);
    }
}
