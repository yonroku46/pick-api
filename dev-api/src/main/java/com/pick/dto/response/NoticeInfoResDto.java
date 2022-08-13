package com.pick.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pick.dto.base.ResponseData;
import com.pick.entity.Notice;
import com.pick.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;


@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class NoticeInfoResDto extends ResponseData {
    private Integer noticeCd;
    private String category;
    private String noticeTitle;
    private String noticeContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm")
    private Timestamp updateTime;
    private Integer activeFlag;
    private String userName;
    private String userInfo;

    public NoticeInfoResDto(Notice notice, User user) {
        this.noticeCd = notice.getNoticeCd();
        this.category = notice.getCategory();
        this.noticeTitle = notice.getNoticeTitle();
        this.noticeContent = notice.getNoticeContent();
        this.updateTime = notice.getUpdateTime();
        this.activeFlag = notice.getActiveFlag();
        this.userName = user.getUserName();
        this.userInfo = user.getUserInfo();
    }
}
