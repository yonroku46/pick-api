package com.pick.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class TalkRoomListReqDto {
    @Positive
    private Integer page = 1;
}
