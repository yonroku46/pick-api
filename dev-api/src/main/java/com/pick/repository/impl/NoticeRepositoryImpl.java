package com.pick.repository.impl;

import com.pick.dto.response.NoticeResDto;
import com.pick.entity.Notice;
import com.pick.repository.NoticeRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.pick.entity.QNotice.notice;
import static com.pick.entity.QTalkContent.talkContent;
import static com.pick.entity.QUser.user;

@Repository
public class NoticeRepositoryImpl implements NoticeRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public NoticeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Boolean saveNotice(Notice notice) {
        entityManager.persist(notice);
        return true;
    }

    public Boolean getActive(Integer noticeCd) {
        Integer result =  queryFactory.select(notice.activeFlag)
                .where(notice.noticeCd.eq(noticeCd),
                        notice.deleteFlag.eq(0))
                .fetchOne();
        return result == 0;
    }

    public NoticeResDto getNotice(Integer noticeCd) {
        Notice data = queryFactory.selectFrom(notice)
                .where(notice.noticeCd.eq(noticeCd),
                        notice.deleteFlag.eq(0))
                .fetchOne();
        return new NoticeResDto(data.getCategory(), data.getNoticeTitle(), data.getNoticeContent());
    }

    public List<NoticeResDto> getNoticeList() {
        List<Notice> notices =  queryFactory.selectFrom(notice)
                .where(notice.deleteFlag.eq(0),
                        notice.activeFlag.eq(0))
                .orderBy(notice.createTime.asc())
                .fetch();

        List<NoticeResDto> result = new ArrayList<>();
        for (Notice notice : notices) {
            result.add(new NoticeResDto(notice.getCategory(), notice.getNoticeTitle(), notice.getNoticeContent()));
        }
        return result;
    }

    public List<NoticeResDto> getNoticeListAll() {
        List<Notice> notices =  queryFactory.selectFrom(notice)
                .where(notice.deleteFlag.eq(0))
                .orderBy(notice.createTime.asc())
                .fetch();

        List<NoticeResDto> result = new ArrayList<>();
        for (Notice notice : notices) {
            result.add(new NoticeResDto(notice.getCategory(), notice.getNoticeTitle(), notice.getNoticeContent()));
        }
        return result;
    }

}
