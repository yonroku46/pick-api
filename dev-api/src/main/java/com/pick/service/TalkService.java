package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.response.BooleanResDto;
import com.pick.dto.request.*;

public interface TalkService {
    ResponseData talkRoomList(TalkRoomListReqDto req);
    ResponseData enterTalkRoom(EnterTalkRoomReqDto req) throws IllegalAccessException;
    ResponseData createTalkRoom(CreateTalkReqDto req);
    BooleanResDto sendMessage(SendMessageReqDto req) throws IllegalAccessException;
    BooleanResDto leaveTalkRoom(LeaveTalkRoomReqDto req) throws IllegalAccessException;
}
