package com.pick.chat.service;

import com.pick.chat.dto.ChatContentDto;
import com.pick.chat.dto.ChatRoomDto;

import java.util.List;

public interface ChatService {
    Integer startChat(Integer staffCd);

    List<ChatContentDto> findMessages(Integer chatRoomCd, Integer page) throws IllegalAccessException;

    List<ChatRoomDto> findChatRooms(Integer page);

    void sendMessage(Integer chatRoomCd, String message) throws IllegalAccessException;

    void leaveChatRoom(Integer chatRoomCd) throws IllegalAccessException;
}
