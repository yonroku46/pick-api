package com.pick.repository.impl;

import com.pick.dto.response.NoticeInfoResDto;
import com.pick.dto.response.NoticeResDto;
import com.pick.entity.Notice;
import com.pick.entity.User;
import com.pick.repository.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.pick.entity.QNotice.notice;
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

    public Integer saveNotice(Notice notice) {
        entityManager.persist(notice);
        return notice.getNoticeCd();
    }

    public void editNotice(Notice preNotice) {
        Notice data = queryFactory.selectFrom(notice)
                .where(notice.noticeCd.eq(preNotice.getNoticeCd()))
                .fetchOne();
        data.setNoticeTitle(preNotice.getNoticeTitle());
        data.setCategory(preNotice.getCategory());
        data.setNoticeContent(preNotice.getNoticeContent());
        data.setActiveFlag(preNotice.getActiveFlag());
    }

    public void editActiveNotice(Integer noticeCd, Integer activeFlag) {
        Notice data = queryFactory.selectFrom(notice)
                .where(notice.noticeCd.eq(noticeCd))
                .fetchOne();
        data.setActiveFlag(activeFlag);
    }

    public void deleteNotice(Integer noticeCd) {
        Notice data = queryFactory.selectFrom(notice)
                .where(notice.noticeCd.eq(noticeCd))
                .fetchOne();
        entityManager.remove(data);
    }

    public Integer getActive(Integer noticeCd) {
        Notice data = queryFactory.selectFrom(notice)
                .where(notice.noticeCd.eq(noticeCd),
                        notice.deleteFlag.eq(0))
                .fetchOne();
        return data.getActiveFlag();
    }

    public NoticeInfoResDto getNoticeInfo(Integer noticeCd) {
        Notice noticeData = queryFactory.selectFrom(notice)
                .where(notice.noticeCd.eq(noticeCd),
                        notice.deleteFlag.eq(0))
                .fetchOne();
        User userData = queryFactory.selectFrom(user)
                .where(user.userCd.eq(noticeData.getWriterCd()),
                        user.deleteFlag.eq(0))
                .fetchOne();
        return  new NoticeInfoResDto(noticeData, userData);
    }

    public List<NoticeResDto> getNoticeList(String search) {
        List<Notice> notices =  queryFactory.selectFrom(notice)
                .where(notice.noticeTitle.like(search),
                        notice.deleteFlag.eq(0),
                        notice.activeFlag.eq(1))
                .orderBy(notice.createTime.desc())
                .fetch();
        return notices.stream()
                .map(data -> new NoticeResDto(data))
                .collect(Collectors.toList());
    }

    public List<NoticeResDto> getNoticeListAll(String search) {
        List<Notice> notices =  queryFactory.selectFrom(notice)
                .where(notice.noticeTitle.like(search),
                        notice.deleteFlag.eq(0))
                .orderBy(notice.createTime.desc())
                .fetch();
        return notices.stream()
                .map(data -> new NoticeResDto(data))
                .collect(Collectors.toList());
    }

}
