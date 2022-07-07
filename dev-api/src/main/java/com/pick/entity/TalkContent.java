package com.pick.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "m_talk_content")
@NoArgsConstructor
public class TalkContent {
    @Id
    @Column(name = "talk_content_cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer talkContentCd;

    private Integer talkRoomCd;

    private Integer fromUserCd;
    private Integer toUserCd;

    @Column(columnDefinition = "VARCHAR(500)")
    private String message;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createTime;

    private Integer deleteFlag = 0;

    public void setMessage(String message) {
        if (message.strip().length() > 500) {
            this.message = message.strip().substring(0, 500);
        } else {
            this.message = message.strip();
        }
    }

}
