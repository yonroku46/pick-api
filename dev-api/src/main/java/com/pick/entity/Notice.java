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

    private Integer writerCd;

    private String category;

    private String noticeTitle;

    private String noticeContent;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createTime;

    @UpdateTimestamp
    private Timestamp updateTime;

    private Integer activeFlag = 0;

    private Integer deleteFlag = 0;

}
