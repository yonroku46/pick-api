package com.pick.talk.repository;

import com.pick.talk.dto.response.TalkRoomDto;
import com.pick.talk.entity.TalkContent;
import com.pick.talk.entity.TalkRoom;
import com.pick.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.pick.talk.entity.QTalkContent.talkContent;
import static com.pick.talk.entity.QTalkRoom.talkRoom;
import static com.pick.entity.QUser.user;

@Repository
public class TalkRepositoryImpl implements TalkRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public TalkRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    // 채팅방 저장
    public Integer saveTalkRoom(TalkRoom talkRoom) {
        entityManager.persist(talkRoom);
        return talkRoom.getCd();
    }

    // 메세지 저장
    public Integer saveTalkContent(TalkContent talkContent) {
        entityManager.persist(talkContent);
        return talkContent.getCd();
    }

    // 채팅방 검색
    public TalkRoom findTalkRoomByUserCd(Integer requesterCd, Integer otherSideCd) {
        Integer[] userCds = {requesterCd, otherSideCd};
        return queryFactory.selectFrom(talkRoom)
                .where(talkRoom.hostCd.in(userCds),
                        talkRoom.guestCd.in(userCds),
                        talkRoom.deleteFlag.eq(0))
                .fetchOne();
    }

    // 채팅방 검색
    public TalkRoom findTalkRoomByRoomCd(Integer talkRoomCd) {
        return queryFactory.selectFrom(talkRoom)
                .where(talkRoom.cd.eq(talkRoomCd),
                        talkRoom.deleteFlag.eq(0))
                .fetchOne();
    }

    // 채팅방 목록 검색 (10+1개씩)
    public List<TalkRoomDto> findTalkRoomList(Integer requesterCd, Integer page) {
        List<TalkRoom> rooms = queryFactory.selectFrom(talkRoom)
                .where(talkRoom.hostCd.eq(requesterCd)
                                .or(talkRoom.guestCd.eq(requesterCd)),
                        talkRoom.deleteFlag.eq(0))
                .orderBy(talkRoom.updateTime.desc())
                .limit(11)
                .offset(page)
                .fetch();

        List<Integer> participants = new ArrayList<>();
        for (TalkRoom room : rooms) {
            if (room.getHostCd() == requesterCd) {
                participants.add(room.getGuestCd());
            } else {
                participants.add(room.getHostCd());
            }
        }

        List<User> users = queryFactory.selectFrom(user)
                .where(user.userCd.in(participants))
                .fetch();

        List<TalkRoomDto> result = new ArrayList<>();

        for (TalkRoom room : rooms) {
            if (room.getHostCd() == requesterCd) {
                // 요청자가 호스트
                for (User user : users) {
                    if (user.getUserCd() == room.getGuestCd()) {
                        result.add(new TalkRoomDto(room, user, true));
                    }
                }
            } else {
                // 요청자가 게스트
                for (User user : users) {
                    if (user.getUserCd() == room.getHostCd()) {
                        result.add(new TalkRoomDto(room, user, false));
                    }
                }
            }
        }
        return result;
    }


    // 채팅 내용 검색 (50+1개씩)
    public List<TalkContent> findTalkContents(Integer talkRoomCd, Integer page) {
        return queryFactory.selectFrom(talkContent)
                .where(talkContent.talkRoomCd.eq(talkRoomCd),
                        talkContent.deleteFlag.eq(0))
                .orderBy(talkContent.createTime.desc())
                .limit(51)
                .offset(page)
                .fetch();
    }
}
