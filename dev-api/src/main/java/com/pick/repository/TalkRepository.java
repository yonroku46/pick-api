package com.pick.repository;

import com.pick.dto.response.TalkRoomDto;
import com.pick.entity.TalkContent;
import com.pick.entity.TalkRoom;
import com.pick.dto.response.TalkContentDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface TalkRepository {

    Integer saveTalkRoom(TalkRoom talkRoom);

    Integer saveTalkContent(TalkContent talkContent);

    TalkRoom findTalkRoomByUserCd(Integer requesterCd, Integer otherSideCd);

    TalkRoom findTalkRoomByRoomCd(Integer talkRoomCd);

    List<TalkRoomDto> findTalkRoomList(Integer requesterCd, Integer page);

    List<TalkContentDto> findTalkContents(Integer requesterCd, Integer talkRoomCd, Integer page);

    List<TalkContentDto> reloadTalkContents(Integer requesterCd, Integer talkRoomCd, Integer talkContentCd);
}
