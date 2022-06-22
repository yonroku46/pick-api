package com.pick.chat.repository;

import com.pick.chat.dto.ChatRoomResDto;
import com.pick.chat.entity.ChatContent;
import com.pick.chat.entity.ChatRoom;

import java.util.List;

public interface ChatRepository {
    Integer saveChatRoom(ChatRoom chatRoom);

    Integer saveChatContent(ChatContent chatContent);

    ChatRoom findChatRoomByUserCd(Integer requesterCd, Integer otherSideCd);

    ChatRoom findChatRoomByRoomCd(Integer chatRoomCd);

    List<ChatRoomResDto> findChatRooms(Integer requesterCd, Integer page);

    List<ChatContent> findChatContents(Integer chatRoomCd, Integer page);

    void changeDelFlagOfContents(Integer chatRoomCd);
}
