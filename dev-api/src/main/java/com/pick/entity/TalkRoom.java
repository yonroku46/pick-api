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

    @Column(name = "host_cd")
    private Integer hostCd;

    @Column(name = "host_read_flag")
    private Boolean hostReadFlag = false;

    @Column(name = "guest_cd")
    private Integer guestCd;

    @Column(name = "guest_read_flag")
    private Boolean guestReadFlag = false;

    @Column(name = "latest_message", columnDefinition = "VARCHAR(100)")
    private String latestMessage;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "delete_flag", columnDefinition = "integer default 0")
    private Integer deleteFlag;

    @PrePersist
    public void prePersist(){
        this.deleteFlag = this.deleteFlag == null ? 0: this.deleteFlag;
    }

}
