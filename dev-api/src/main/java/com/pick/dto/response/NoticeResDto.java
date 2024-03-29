package com.pick.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pick.dto.base.ResponseData;
import com.pick.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;


@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class NoticeResDto extends ResponseData {
    private Integer noticeCd;
    private String category;
    private String noticeTitle;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private Timestamp updateTime;
    private Integer activeFlag;

    public NoticeResDto(Notice notice) {
        this.noticeCd = notice.getNoticeCd();
        this.category = notice.getCategory();
        this.noticeTitle = notice.getNoticeTitle();
        this.updateTime = notice.getUpdateTime();
        this.activeFlag = notice.getActiveFlag();
    }
}
