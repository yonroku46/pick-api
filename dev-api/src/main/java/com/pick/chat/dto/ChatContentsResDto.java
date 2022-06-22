package com.pick.chat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pick.chat.entity.ChatContent;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatContentsResDto {
    private Boolean me;
    private String message;
    private Timestamp sendTime;

    // 내가 보낸 메세지인지 체크
    public ChatContentsResDto(ChatContent content, Integer requesterCd) {
        if (content.getFromUserCd() == requesterCd) {
            this.me = true;
        } else {
            this.me = false;
        }
        this.message = content.getMessage();
        this.sendTime = content.getCreateTime();
    }

    public String getSendTime() {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        return simpleDate.format(sendTime);
    }
}
