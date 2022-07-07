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
@Table(name = "m_talk_room")
@NoArgsConstructor
public class TalkRoom {
    @Id
    @Column(name = "talk_room_cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer talkRoomCd;

    private Integer hostCd;
    private Boolean hostReadFlag = false;

    private Integer guestCd;
    private Boolean guestReadFlag = false;

    @Column(columnDefinition = "VARCHAR(100)")
    private String latestMessage;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createTime;

    @UpdateTimestamp
    private Timestamp updateTime;

    private Integer deleteFlag = 0; // 0 : 삭제안됨 1 : 삭제

    public void setLatestMessage(String latestMessage) {
        if (latestMessage.strip().length() > 100) {
            this.latestMessage = latestMessage.strip().substring(0, 100);
        } else {
            this.latestMessage = latestMessage.strip();
        }
    }
}
