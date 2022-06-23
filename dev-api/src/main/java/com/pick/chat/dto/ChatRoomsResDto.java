package com.pick.chat.dto;

import com.pick.dto.base.ResponseData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChatRoomsResDto extends ResponseData {
    private List<ChatRoomDto> chatRoomDtos;
    private boolean hasNext;

    public ChatRoomsResDto(List<ChatRoomDto> chatRoomDtos) {
        if (chatRoomDtos.size() > 10) {
            this.hasNext = true;
            chatRoomDtos.remove(10);
        } else {
            this.hasNext = false;
        }
        this.chatRoomDtos = chatRoomDtos;
    }
}
