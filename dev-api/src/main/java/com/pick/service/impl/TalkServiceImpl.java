package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;
import com.pick.dto.response.*;
import com.pick.entity.User;
import com.pick.model.TalkContentDto;
import com.pick.repository.UserRepository;
import com.pick.security.bean.SecurityUserDtoLoader;
import com.pick.entity.TalkRoom;
import com.pick.repository.TalkRepository;
import com.pick.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TalkServiceImpl implements TalkService {
    private final TalkRepository talkRepository;
    private final UserRepository userRepository;
    private final SecurityUserDtoLoader securityUserDtoLoader;

    // 채팅방 목록 검색 (10+1개씩)
    @Override
    public ResponseData talkRoomList(TalkRoomListReqDto req) {
        List<TalkRoomDto> talkRoomList = talkRepository.findTalkRoomList(securityUserDtoLoader.getUserCd(), req.getPage() - 1);
        return new TalkRoomsResDto(talkRoomList);
    }

    // 채팅방 입장
    @Override
    public ResponseData enterTalkRoom(EnterTalkRoomReqDto req) throws IllegalAccessException {
        Integer talkRoomCd = req.getTalkRoomCd();
        Integer page = req.getPage();
        // 본인 확인
        verifyParticipant(talkRoomCd);
        // 메세지 '읽음'으로 변경
        requesterReadMessage(talkRoomCd);

        // 채팅 내용 (50+1개) 리턴
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        List<TalkContentDto> response = talkRepository.findTalkContents(requesterCd, talkRoomCd, page - 1);
        return new TalkContentsResDto(response);
    }

    // 채팅 시작
    @Override
    public ResponseData createTalkRoom(CreateTalkReqDto req) {
        Integer toUserCd = req.getToUserCd();
        User user = userRepository.findUserByUserCd(toUserCd);
        TalkRoomResDto response = new TalkRoomResDto();

        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        TalkRoom targetRoom = talkRepository.findTalkRoomByUserCd(requesterCd, toUserCd);

        // 채팅방 이미 존재하면 채팅방 코드 리턴, 존재하지 않으면 생성후 채팅방 코드 리턴
        if (targetRoom != null) {
            response.setTalkRoomCd(targetRoom.getTalkRoomCd());
            return response;
        } else {
            TalkRoom newRoom = new TalkRoom();
            newRoom.setHostCd(requesterCd);
            newRoom.setGuestCd(toUserCd);
            response.setTalkRoomCd(talkRepository.saveTalkRoom(newRoom));
            return response;
        }
    }

    // 메세지 전송
    @Override
    public BooleanResDto sendMessage(SendMessageReqDto req) throws IllegalAccessException {
        Integer talkRoomCd = req.getTalkRoomCd();
        String message = req.getMessage();
        // 본인 확인
        TalkRoom room = verifyParticipant(talkRoomCd);
        // 메세지 객체 생성
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        com.pick.entity.TalkContent content = new com.pick.entity.TalkContent();
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

    // 채팅방 나가기 (room의 deleteFlag만 변경)
    @Modifying(clearAutomatically = true)
    @Override
    public BooleanResDto leaveTalkRoom(LeaveTalkRoomReqDto req) throws IllegalAccessException {
        // 본인 확인
        TalkRoom room = verifyParticipant(req.getTalkRoomCd());
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
    private void requesterReadMessage(Integer talkRoomCd) {
        Integer requesterCd = securityUserDtoLoader.getUserCd();
        TalkRoom room = talkRepository.findTalkRoomByRoomCd(talkRoomCd);
        if (requesterCd == room.getHostCd()) {
            room.setHostReadFlag(true);
        } else {
            room.setGuestReadFlag(true);
        }
    }


}
