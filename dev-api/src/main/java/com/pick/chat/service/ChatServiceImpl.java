package com.pick.chat.service;

import com.pick.chat.dto.ChatContentDto;
import com.pick.chat.dto.ChatRoomDto;
import com.pick.chat.entity.ChatContent;
import com.pick.chat.entity.ChatRoom;
import com.pick.chat.repository.ChatRepository;
import com.pick.security.bean.SecurityUserDtoLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final SecurityUserDtoLoader securityUserDtoLoader;

    // 채팅(문의) 시작
    public Integer startChat(Integer toUserCd) {
        // 채팅방 이미 존재?
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        ChatRoom foundRoom = chatRepository.findChatRoomByUserCd(requesterCd, toUserCd);

        // 채팅방 이미 존재할 때 -> 존재하는 채팅방 chatRoomCd 리턴
        if (foundRoom != null) {
            return foundRoom.getCd();
        }

        // 채팅방이 존재하지 않을 때 -> 새로 생성
        ChatRoom newRoom = new ChatRoom();
        newRoom.setHostCd(requesterCd);
        newRoom.setGuestCd(toUserCd);

        // 새로 생성된 chatRoomCd 리턴
        return chatRepository.saveChatRoom(newRoom);
    }

    // 채팅방 입장
    public List<ChatContentDto> findMessages(Integer chatRoomCd, Integer page) throws IllegalAccessException {
        verifyParticipant(chatRoomCd);

        // 메세지 '읽음'으로 변경
        requestorReadMessage(chatRoomCd);

        // 채팅 내용 (50+1개) 리턴
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        List<ChatContent> chatContents = chatRepository.findChatContents(chatRoomCd, page - 1);
        return chatContents.stream()
                .map(e -> new ChatContentDto(e, requesterCd))
                .collect(Collectors.toList());
    }

    // 채팅방 목록 검색 (10+1개씩)
    public List<ChatRoomDto> findChatRooms(Integer page) {
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        return chatRepository.findChatRooms(requesterCd, page - 1);
    }

    // 메세지 전송
    public void sendMessage(Integer chatRoomCd, String message) throws IllegalAccessException {
        ChatRoom room = verifyParticipant(chatRoomCd);
        // 메세지 객체 생성
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        ChatContent content = new ChatContent();
        content.setChatRoomCd(chatRoomCd);
        content.setMessage(message);
        content.setFromUserCd(requesterCd);

        if (requesterCd == room.getHostCd()) {
            // 내가 host일 때
            room.setHostReadFlag(true);
            room.setGuestReadFlag(false);
            content.setToUserCd(room.getGuestCd());
        } else {
            // 내가 guest일 때
            room.setGuestReadFlag(true);
            room.setHostReadFlag(false);
            content.setToUserCd(room.getHostCd());
        }
        room.setLatestMessage(message);
        chatRepository.saveChatContent(content);
    }


    // 채팅방 나가기 -> deleteFlag만 변경
    @Modifying(clearAutomatically = true)
    public void leaveChatRoom(Integer chatRoomCd) throws IllegalAccessException {
        ChatRoom room = verifyParticipant(chatRoomCd);
        room.setDeleteFlag(1);
        chatRepository.changeDelFlagOfContents(chatRoomCd);
    }

    // 요청자가 채팅방의 참여자가 맞는지 확인
    private ChatRoom verifyParticipant(Integer chatRoomCd) throws IllegalAccessException {
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        ChatRoom room = chatRepository.findChatRoomByRoomCd(chatRoomCd);
        if (requesterCd != room.getHostCd() && requesterCd != room.getGuestCd()) {
            throw new IllegalAccessException("잘못된 접근입니다.");
        }
        return room;
    }

    // 요청자만 '읽음'으로 변경
    private void requestorReadMessage(Integer chatRoomCd) {
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        ChatRoom room = chatRepository.findChatRoomByRoomCd(chatRoomCd);
        if (requesterCd == room.getHostCd()) {
            room.setHostReadFlag(true);
        } else {
            room.setGuestReadFlag(true);
        }
    }

}
