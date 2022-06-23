package com.pick.chat.repository;

import com.pick.chat.dto.ChatRoomDto;
import com.pick.chat.entity.ChatContent;
import com.pick.chat.entity.ChatRoom;
import com.pick.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.pick.chat.entity.QChatContent.chatContent;
import static com.pick.chat.entity.QChatRoom.chatRoom;
import static com.pick.entity.QUser.user;

@Repository
public class ChatRepositoryImpl implements ChatRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ChatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    // 채팅방 저장
    public Integer saveChatRoom(ChatRoom chatRoom) {
        entityManager.persist(chatRoom);
        return chatRoom.getCd();
    }

    // 메세지 저장
    public Integer saveChatContent(ChatContent chatContent) {
        entityManager.persist(chatContent);
        return chatContent.getCd();
    }

    // 채팅방 검색
    public ChatRoom findChatRoomByUserCd(Integer requesterCd, Integer otherSideCd) {
        Integer[] userCds = {requesterCd, otherSideCd};
        return queryFactory.selectFrom(chatRoom)
                .where(chatRoom.hostCd.in(userCds),
                        chatRoom.guestCd.in(userCds),
                        chatRoom.deleteFlag.eq(0))
                .fetchOne();
    }

    // 채팅방 검색
    public ChatRoom findChatRoomByRoomCd(Integer chatRoomCd) {
        return queryFactory.selectFrom(chatRoom)
                .where(chatRoom.cd.eq(chatRoomCd),
                        chatRoom.deleteFlag.eq(0))
                .fetchOne();
    }

    // 채팅방 목록 검색 (10+1개씩)
    public List<ChatRoomDto> findChatRooms(Integer requesterCd, Integer page) {
        List<ChatRoom> rooms = queryFactory.selectFrom(chatRoom)
                .where(chatRoom.hostCd.eq(requesterCd)
                                .or(chatRoom.guestCd.eq(requesterCd)),
                        chatRoom.deleteFlag.eq(0))
                .orderBy(chatRoom.updateTime.desc())
                .limit(11)
                .offset(page)
                .fetch();

        List<Integer> participants = new ArrayList<>();
        for (ChatRoom room : rooms) {
            if (room.getHostCd() == requesterCd) {
                participants.add(room.getGuestCd());
            } else {
                participants.add(room.getHostCd());
            }
        }

        List<User> users = queryFactory.selectFrom(user)
                .where(user.userCd.in(participants))
                .fetch();

        List<ChatRoomDto> result = new ArrayList<>();

        for (ChatRoom room : rooms) {
            if (room.getHostCd() == requesterCd) {
                // 요청자가 호스트
                for (User user : users) {
                    if (user.getUserCd() == room.getGuestCd()) {
                        result.add(new ChatRoomDto(room, user, true));
                    }
                }
            } else {
                // 요청자가 게스트
                for (User user : users) {
                    if (user.getUserCd() == room.getHostCd()) {
                        result.add(new ChatRoomDto(room, user, false));
                    }
                }
            }
        }
        return result;
    }


    // 채팅 내용 검색 (50+1개씩)
    public List<ChatContent> findChatContents(Integer chatRoomCd, Integer page) {
        return queryFactory.selectFrom(chatContent)
                .where(chatContent.chatRoomCd.eq(chatRoomCd),
                        chatContent.deleteFlag.eq(0))
                .orderBy(chatContent.createTime.desc())
                .limit(51)
                .offset(page)
                .fetch();
    }

    // 채팅방 나가기시 대화내용 삭제
    public void changeDelFlagOfContents(Integer chatRoomCd) {
        queryFactory.update(chatContent)
                .set(chatContent.deleteFlag, 1)
                .where(chatContent.chatRoomCd.eq(chatRoomCd))
                .execute();
    }
}
