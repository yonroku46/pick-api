package com.pick.chat.Controller;

import com.pick.chat.dto.ChatContentDto;
import com.pick.chat.dto.ChatContentsResDto;
import com.pick.chat.dto.ChatRoomDto;
import com.pick.chat.dto.ChatRoomsResDto;
import com.pick.chat.service.ChatService;
import com.pick.entity.base.SingleResponse;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/***
 * page는 프론트단에서 1부터, 백단에서 0부터 시작
 */
@RestController
@RequestMapping("/api/chat")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final ResponseService responseService;

    // 채팅방 목록 -> ChatRoomResDto 리턴
    @GetMapping("/rooms")
    public SingleResponse<ChatRoomsResDto> chatRooms(@RequestParam(required = false, defaultValue = "1") Integer page) {
        List<ChatRoomDto> chatRooms = chatService.findChatRooms(page);
        return responseService.getSingleResponse(new ChatRoomsResDto(chatRooms));
    }

    // 채팅내용 출력 -> ChatContentsResDto 리스트 리턴
    @GetMapping("/{chatRoomCd}")
    public SingleResponse<ChatContentsResDto> enterChatRoom(@PathVariable Integer chatRoomCd,
                                                            @RequestParam(required = false, defaultValue = "1") Integer page) throws IllegalAccessException {
        List<ChatContentDto> messages = chatService.findMessages(chatRoomCd, page);
        return responseService.getSingleResponse(new ChatContentsResDto(messages));
    }

    // 채팅방 새로 생성 -> chatRoomCd 리턴 -> redirect하기
    @GetMapping("/create/{toUserCd}")
    public SingleResponse<Integer> startChat(@PathVariable Integer toUserCd) {
        return responseService.getSingleResponse(chatService.startChat(toUserCd));
    }

    // 메세지 보내기 -> Boolean 리턴
    @PostMapping("/{chatRoomCd}/send")
    public SingleResponse<Boolean> sendMessage(@PathVariable Integer chatRoomCd,
                                               @RequestBody HashMap<String, String> messageMap) throws IllegalAccessException {
        chatService.sendMessage(chatRoomCd, messageMap.get("message"));
        return responseService.getSingleResponse(true);
    }

    // 대화방 나가기 -> 대화방 목록 페이지로 redirect
    @PostMapping("/{chatRoomCd}/leave")
    public SingleResponse<Boolean> leaveChatRoom(@PathVariable Integer chatRoomCd) throws IllegalAccessException {
        chatService.leaveChatRoom(chatRoomCd);
        return responseService.getSingleResponse(true);
    }
}
