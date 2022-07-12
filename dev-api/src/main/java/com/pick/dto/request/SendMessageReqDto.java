package com.pick.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class SendMessageReqDto {
    @Positive
    private Integer talkRoomCd;
    @NotBlank
    private String message;
}
