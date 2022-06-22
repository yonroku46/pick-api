package com.pick.chat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pick.chat.entity.ChatRoom;
import com.pick.dto.base.ResponseData;
import com.pick.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRoomResDto extends ResponseData {
    // 상대방 계정 정보
    private Integer userCd;
    private String username;
    private String img;

    // chatRoom 정보
    private Integer chatRoomCd;
    private String message;
    private Timestamp updateTime;
    private Boolean read;

    public ChatRoomResDto(ChatRoom chatRoom, User user, boolean isHost) {
        this.userCd = user.getUserCd();
        this.username = user.getUserName();
        this.img = user.getUserImg();


        this.chatRoomCd = chatRoom.getCd();
        this.message = chatRoom.getLatestMessage();
        this.updateTime = chatRoom.getUpdateTime();

        if (isHost) {
            this.read = chatRoom.getHostReadFlag();
        } else {
            this.read = chatRoom.getGuestReadFlag();
        }
    }
}
