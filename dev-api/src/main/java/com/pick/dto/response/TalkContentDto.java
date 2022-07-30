package com.pick.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pick.dto.base.ResponseData;
import com.pick.entity.TalkContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TalkContentDto extends ResponseData {
    private Integer talkContentCd;
    private Boolean me;

    private String userName;
    private String userImg;
    private String message;
    private Timestamp sendTime;
    private TalkContent content;

    // 내가 보낸 메세지인지 체크
    public TalkContentDto(TalkContent content, String userName, String userImg) {
        this.talkContentCd = content.getTalkContentCd();
        this.content = content;
        this.message = content.getMessage();
        this.sendTime = content.getCreateTime();
        this.userName = userName;
        this.userImg = userImg;
    }

    public void meCheck(Integer requesterCd) {
        this.me = content.getFromUserCd() == requesterCd;
        this.content = null;
    }

    public String getSendTime() {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일/HH:mm");
        return simpleDate.format(sendTime);
    }
}
