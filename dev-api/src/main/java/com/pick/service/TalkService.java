package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.response.BooleanResDto;
import com.pick.dto.request.*;
import com.pick.dto.response.TalkContentDto;
import com.pick.dto.response.TalkRoomDto;

import java.util.List;

public interface TalkService {
    List<TalkRoomDto> talkRoomList(TalkRoomListReqDto req);
    List<TalkContentDto> enterTalkRoom(EnterTalkRoomReqDto req) throws IllegalAccessException;
    ResponseData createTalkRoom(CreateTalkReqDto req);
    BooleanResDto sendMessage(SendMessageReqDto req) throws IllegalAccessException;
    BooleanResDto leaveTalkRoom(LeaveTalkRoomReqDto req) throws IllegalAccessException;
    List<TalkContentDto> reloadTalkContents(ReloadContentsReqDto req) throws IllegalAccessException;
}
