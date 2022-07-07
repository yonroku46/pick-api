package com.pick.repository.impl;

import com.pick.dto.response.TalkRoomDto;
import com.pick.entity.TalkContent;
import com.pick.entity.TalkRoom;
import com.pick.entity.User;
import com.pick.dto.response.TalkContentDto;
import com.pick.repository.TalkRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.pick.entity.QTalkContent.talkContent;
import static com.pick.entity.QTalkRoom.talkRoom;
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

    public Integer saveTalkRoom(TalkRoom talkRoom) {
        entityManager.persist(talkRoom);
        return talkRoom.getTalkRoomCd();
    }

    public Integer saveTalkContent(TalkContent talkContent) {
        entityManager.persist(talkContent);
        return talkContent.getTalkContentCd();
    }

    public TalkRoom findTalkRoomByUserCd(Integer requesterCd, Integer otherSideCd) {
        Integer[] userCds = {requesterCd, otherSideCd};
        return queryFactory.selectFrom(talkRoom)
                .where(talkRoom.hostCd.in(userCds),
                        talkRoom.guestCd.in(userCds),
                        talkRoom.deleteFlag.eq(0))
                .fetchOne();
    }

    public TalkRoom findTalkRoomByRoomCd(Integer talkRoomCd) {
        return queryFactory.selectFrom(talkRoom)
                .where(talkRoom.talkRoomCd.eq(talkRoomCd),
                        talkRoom.deleteFlag.eq(0))
                .fetchOne();
    }

    public List<TalkRoomDto> findTalkRoomList(Integer requesterCd, Integer page) {
        List<TalkRoom> rooms = queryFactory.selectFrom(talkRoom)
                .where(talkRoom.hostCd.eq(requesterCd)
                                .or(talkRoom.guestCd.eq(requesterCd)),
                        talkRoom.deleteFlag.eq(0))
                .orderBy(talkRoom.updateTime.desc())
                .limit(10)
                .offset(page * 10)
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

    public List<TalkContentDto> findTalkContents(Integer requesterCd, Integer talkRoomCd, Integer page) {
        List<TalkContentDto> contents = queryFactory
                .select(Projections.constructor(TalkContentDto.class,
                        talkContent, user.userName, user.userImg))
                .from(talkContent)
                .join(user).on(talkContent.fromUserCd.eq(user.userCd))
                .where(talkContent.talkRoomCd.eq(talkRoomCd),
                        talkContent.deleteFlag.eq(0))
                .orderBy(talkContent.createTime.asc())
                .limit(50)
                .offset(page * 50)
                .fetch();
        contents.forEach(e -> e.meCheck(requesterCd));
        return contents;
    }

    public List<TalkContentDto> reloadTalkContents(Integer requesterCd, Integer talkRoomCd, Integer talkContentCd) {
        List<TalkContentDto> contents = queryFactory
                .select(Projections.constructor(TalkContentDto.class,
                        talkContent, user.userName, user.userImg))
                .from(talkContent)
                .join(user).on(talkContent.fromUserCd.eq(user.userCd))
                .where(talkContent.talkRoomCd.eq(talkRoomCd),
                        talkContent.deleteFlag.eq(0),
                        talkContent.talkContentCd.gt(talkContentCd))
                .orderBy(talkContent.createTime.asc())
                .fetch();
        contents.forEach(e -> e.meCheck(requesterCd));
        return contents;
    }
}
