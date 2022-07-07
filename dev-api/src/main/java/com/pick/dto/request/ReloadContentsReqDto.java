package com.pick.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReloadContentsReqDto {
    @Positive
    private Integer talkRoomCd;
    @Positive
    private Integer lastContentCd;
}

