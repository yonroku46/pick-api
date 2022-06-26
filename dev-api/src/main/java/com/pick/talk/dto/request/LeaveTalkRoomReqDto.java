package com.pick.talk.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class LeaveTalkRoomReqDto {
    @Positive
    private Integer talkRoomCd;
}
