package com.pick.talk.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pick.talk.entity.TalkContent;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TalkContentDto {
    private Boolean me;

    private String userName;
    private String userImg;
    private String message;
    private Timestamp sendTime;
    private TalkContent content;

    // 내가 보낸 메세지인지 체크
    public TalkContentDto(TalkContent content, String userName, String userImg) {
        this.content = content;
        this.message = content.getMessage();
        this.sendTime = content.getCreateTime();
        this.userName = userName;
        this.userImg = userImg;
    }

    public void meCheck(Integer requesterCd) {
        if (content.getFromUserCd() == requesterCd) {
            this.me = true;
        } else {
            this.me = false;
        }
        this.content = null;
    }

    public String getSendTime() {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        return simpleDate.format(sendTime);
    }
}
