package com.pick.talk.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.response.BooleanResDto;
import com.pick.talk.dto.request.*;

public interface TalkService {
    ResponseData createTalkRoom(CreateTalkReqDto createTalkReqDto);

    ResponseData findMessages(EnterTalkRoomReqDto enterTalkRoomReqDto) throws IllegalAccessException;

    ResponseData findTalkRoomList(TalkRoomListReqDto talkRoomListReqDto);

    BooleanResDto sendMessage(SendMessageReqDto sendMessageReqDto) throws IllegalAccessException;

    BooleanResDto leaveTalkRoom(LeaveTalkRoomReqDto leaveTalkRoomReqDto) throws IllegalAccessException;
}
