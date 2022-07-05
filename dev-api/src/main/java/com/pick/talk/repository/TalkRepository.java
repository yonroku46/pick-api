package com.pick.talk.repository;

import com.pick.talk.dto.response.TalkContentDto;
import com.pick.talk.dto.response.TalkRoomDto;
import com.pick.talk.entity.TalkContent;
import com.pick.talk.entity.TalkRoom;

import java.util.List;

public interface TalkRepository {
    Integer saveTalkRoom(TalkRoom talkRoom);

    Integer saveTalkContent(TalkContent talkContent);

    TalkRoom findTalkRoomByUserCd(Integer requesterCd, Integer otherSideCd);

    TalkRoom findTalkRoomByRoomCd(Integer talkRoomCd);

    List<TalkRoomDto> findTalkRoomList(Integer requesterCd, Integer page);

    List<TalkContentDto> findTalkContents(Integer requesterCd, Integer talkRoomCd, Integer page);
}
