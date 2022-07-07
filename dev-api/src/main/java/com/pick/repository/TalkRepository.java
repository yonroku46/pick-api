package com.pick.repository;

import com.pick.dto.response.TalkRoomDto;
import com.pick.entity.TalkContent;
import com.pick.entity.TalkRoom;
import com.pick.model.TalkContentDto;

import java.util.List;

public interface TalkRepository {
    Integer saveTalkRoom(TalkRoom talkRoom);
    Integer saveTalkContent(TalkContent talkContent);
    TalkRoom findTalkRoomByUserCd(Integer requesterCd, Integer otherSideCd);
    TalkRoom findTalkRoomByRoomCd(Integer talkRoomCd);
    List<TalkRoomDto> findTalkRoomList(Integer requesterCd, Integer page);
    List<TalkContentDto> findTalkContents(Integer requesterCd, Integer talkRoomCd, Integer page) ;
}
