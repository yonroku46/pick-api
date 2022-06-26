package com.pick.talk.service;

import com.pick.entity.User;
import com.pick.repository.UserRepository;
import com.pick.talk.dto.request.*;
import com.pick.talk.dto.response.TalkContentDto;
import com.pick.talk.dto.response.TalkRoomDto;
import com.pick.talk.entity.TalkContent;
import com.pick.talk.entity.TalkRoom;
import com.pick.talk.repository.TalkRepository;
import com.pick.dto.response.BooleanResDto;
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
public class TalkServiceImpl implements TalkService {
    private final TalkRepository talkRepository;
    private final UserRepository userRepository;
    private final SecurityUserDtoLoader securityUserDtoLoader;

    // 채팅 시작
    @Override
    public Integer createTalkRoom(CreateTalkReqDto createTalkReqDto) {
        Integer toUserCd = createTalkReqDto.getToUserCd();
        // 상대가 존재하는 유저?
        User user = userRepository.findUserByUserCd(toUserCd);
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        Integer requesterCd = securityUserDtoLoader.getUserCd();
        TalkRoom foundRoom = talkRepository.findTalkRoomByUserCd(requesterCd, toUserCd);
        // 채팅방 이미 존재할 때 -> 존재하는 채팅방 talkRoomCd 리턴
        if (foundRoom != null) {
            return foundRoom.getCd();
        }
        // 채팅방이 존재하지 않을 때 -> 새로 생성
        TalkRoom newRoom = new TalkRoom();
        newRoom.setHostCd(requesterCd);
        newRoom.setGuestCd(toUserCd);
        // 새로 생성된 talkRoomCd 리턴
        return talkRepository.saveTalkRoom(newRoom);
    }

    // 채팅방 입장
    @Override
    public List<TalkContentDto> findMessages(EnterTalkRoomReqDto enterTalkRoomReqDto) throws IllegalAccessException {
        Integer talkRoomCd = enterTalkRoomReqDto.getTalkRoomCd();
        Integer page = enterTalkRoomReqDto.getPage();
        // 본인 확인
        verifyParticipant(talkRoomCd);

        // 메세지 '읽음'으로 변경
        requestorReadMessage(talkRoomCd);

        // 채팅 내용 (50+1개) 리턴
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        List<TalkContent> talkContents = talkRepository.findTalkContents(talkRoomCd, page - 1);
        return talkContents.stream()
                .map(e -> new TalkContentDto(e, requesterCd))
                .collect(Collectors.toList());
    }

    // 채팅방 목록 검색 (10+1개씩)
    @Override
    public List<TalkRoomDto> findTalkRoomList(TalkRoomListReqDto talkRoomListReqDto) {
        return talkRepository.findTalkRoomList(securityUserDtoLoader.getUserCd(), talkRoomListReqDto.getPage() - 1);
    }

    // 메세지 전송
    @Override
    public BooleanResDto sendMessage(SendMessageReqDto sendMessageReqDto) throws IllegalAccessException {
        Integer talkRoomCd = sendMessageReqDto.getTalkRoomCd();
        String message = sendMessageReqDto.getMessage();
        // 본인 확인
        TalkRoom room = verifyParticipant(talkRoomCd);
        // 메세지 객체 생성
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        TalkContent content = new TalkContent();
        content.setTalkRoomCd(talkRoomCd);
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
        talkRepository.saveTalkContent(content);
        return new BooleanResDto(true);
    }


    // 채팅방 나가기 -> room의 deleteFlag만 변경
    @Modifying(clearAutomatically = true)
    @Override
    public BooleanResDto leaveTalkRoom(LeaveTalkRoomReqDto leaveTalkRoomReqDto) throws IllegalAccessException {
        // 본인 확인
        TalkRoom room = verifyParticipant(leaveTalkRoomReqDto.getTalkRoomCd());
        room.setDeleteFlag(1);
        return new BooleanResDto(true);
    }

    // 요청자가 채팅방의 참여자가 맞는지 확인
    private TalkRoom verifyParticipant(Integer talkRoomCd) throws IllegalAccessException {
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        TalkRoom room = talkRepository.findTalkRoomByRoomCd(talkRoomCd);
        if (requesterCd != room.getHostCd() && requesterCd != room.getGuestCd()) {
            throw new IllegalAccessException("잘못된 접근입니다.");
        }
        return room;
    }

    // 요청자만 '읽음'으로 변경
    private void requestorReadMessage(Integer talkRoomCd) {
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        TalkRoom room = talkRepository.findTalkRoomByRoomCd(talkRoomCd);
        if (requesterCd == room.getHostCd()) {
            room.setHostReadFlag(true);
        } else {
            room.setGuestReadFlag(true);
        }
    }


}
