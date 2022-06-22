package com.pick.chat.service;

import com.pick.chat.dto.ChatContentsResDto;
import com.pick.chat.dto.ChatRoomResDto;

import java.util.List;

public interface ChatService {
    Integer startChat(Integer staffCd);

    List<ChatContentsResDto> findMessages(Integer chatRoomCd, Integer page) throws IllegalAccessException;

    List<ChatRoomResDto> findChatRooms(Integer page);

    void sendMessage(Integer chatRoomCd, String message) throws IllegalAccessException;

    void leaveChatRoom(Integer chatRoomCd) throws IllegalAccessException;
}
