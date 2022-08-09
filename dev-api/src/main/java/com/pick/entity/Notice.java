package com.pick.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "m_notice")
@NoArgsConstructor
public class Notice {

    @Id
    @Column(name = "notice_cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeCd;

    @Column(name = "writer_cd")
    private Integer writerCd;

    @Column(name = "category")
    private String category;

    @Column(name = "notice_title", columnDefinition = "TEXT")
    private String noticeTitle;

    @Column(name = "notice_content", columnDefinition = "TEXT")
    private String noticeContent;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "active_flag")
    private Integer activeFlag;

    @Column(name = "delete_flag", columnDefinition = "integer default 0")
    private Integer deleteFlag;

    @PrePersist
    public void prePersist(){
        this.deleteFlag = this.deleteFlag == null ? 0: this.deleteFlag;
    }

}
