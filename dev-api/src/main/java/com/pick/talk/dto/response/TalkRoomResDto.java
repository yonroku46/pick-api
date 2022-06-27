package com.pick.talk.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalkRoomResDto extends ResponseData {
    private Integer talkRoomCd;
}
