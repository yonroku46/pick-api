package com.pick.chat.dto;

import com.pick.chat.entity.ChatRoom;
import com.pick.dto.base.ResponseData;
import com.pick.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Setter
@Getter
@NoArgsConstructor
public class ChatRoomDto extends ResponseData {
    // 상대방 계정 정보
    private Integer userCd;
    private String username;
    private String img;

    // chatRoom 정보
    private Integer chatRoomCd;
    private String message;
    private Timestamp updateTime;
    private Timestamp updateTimeAsString;
    private Boolean read;

    public ChatRoomDto(ChatRoom chatRoom, User user, boolean isHost) {
        this.userCd = user.getUserCd();
        this.username = user.getUserName();
        this.img = user.getUserImg();


        this.chatRoomCd = chatRoom.getCd();
        this.message = chatRoom.getLatestMessage();
        this.updateTime = chatRoom.getUpdateTime();
        this.updateTimeAsString = chatRoom.getUpdateTime();

        if (isHost) {
            this.read = chatRoom.getHostReadFlag();
        } else {
            this.read = chatRoom.getGuestReadFlag();
        }
    }

    public String getUpdateTimeAsString() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat compDate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        String simpleNow = compDate.format(now);
        String simpleUpdateTime = compDate.format(updateTimeAsString);

        if (updateTime == null) {
            return null;
        }
        // 올해가 아닐 때 : yyyy년 MM월 dd일
        if (!simpleNow.substring(0, 5).equals(simpleUpdateTime.substring(0, 5))) {
            return simpleUpdateTime.substring(0, 13);
        }
        // 올해이고 오늘이 아닐 때 : MM월 dd일
        if (!simpleNow.substring(6, 13).equals(simpleUpdateTime.substring(6, 13))) {
            return simpleUpdateTime.substring(6, 13);
        }
        // 올해이고 오늘이고 시, 분이 다를 때 : HH시 mm분
        if (!simpleNow.substring(14, 21).equals(simpleUpdateTime.substring(14, 21))) {
            return simpleUpdateTime.substring(14, 21);
        }
        // 그 외 : 방금
        return "방금";
    }
}
