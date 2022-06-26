package com.pick.talk.service;

import com.pick.talk.dto.request.*;
import com.pick.talk.dto.response.TalkContentDto;
import com.pick.talk.dto.response.TalkRoomDto;
import com.pick.dto.response.BooleanResDto;

import java.util.List;

public interface TalkService {
    Integer createTalkRoom(CreateTalkReqDto createTalkReqDto);

    List<TalkContentDto> findMessages(EnterTalkRoomReqDto enterTalkRoomReqDto) throws IllegalAccessException;

    List<TalkRoomDto> findTalkRoomList(TalkRoomListReqDto talkRoomListReqDto);

    BooleanResDto sendMessage(SendMessageReqDto sendMessageReqDto) throws IllegalAccessException;

    BooleanResDto leaveTalkRoom(LeaveTalkRoomReqDto leaveTalkRoomReqDto) throws IllegalAccessException;
}
