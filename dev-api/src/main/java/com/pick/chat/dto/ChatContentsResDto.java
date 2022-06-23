package com.pick.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChatContentsResDto {
    private List<ChatContentDto> chatContentDtos;
    private boolean hasNext;

    public ChatContentsResDto(List<ChatContentDto> chatContentDtos) {
        if (chatContentDtos.size() > 50) {
            this.hasNext = true;
            chatContentDtos.remove(50);
        } else {
            this.hasNext = false;
        }
        this.chatContentDtos = chatContentDtos;
    }
}
