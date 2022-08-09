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

    @Column(name = "talk_room_cd")
    private Integer talkRoomCd;

    @Column(name = "from_user_cd")
    private Integer fromUserCd;

    @Column(name = "to_user_cd")
    private Integer toUserCd;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private Timestamp createTime;

    @Column(name = "delete_flag", columnDefinition = "integer default 0")
    private Integer deleteFlag;

    @PrePersist
    public void prePersist(){
        this.deleteFlag = this.deleteFlag == null ? 0: this.deleteFlag;
    }

}
