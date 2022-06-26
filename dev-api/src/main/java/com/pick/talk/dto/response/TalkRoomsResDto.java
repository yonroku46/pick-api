package com.pick.talk.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TalkRoomsResDto extends ResponseData {
    private List<TalkRoomDto> talkRoomList;
    private boolean hasNext;

    public TalkRoomsResDto(List<TalkRoomDto> talkRoomList) {
        if (talkRoomList.size() > 10) {
            this.hasNext = true;
            talkRoomList.remove(10);
        } else {
            this.hasNext = false;
        }
        this.talkRoomList = talkRoomList;
    }
}
