package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;
import com.pick.dto.response.*;
import com.pick.entity.Notice;
import com.pick.enums.RoleEnum;
import com.pick.repository.NoticeRepository;
import com.pick.security.bean.SecurityUserDtoLoader;
import com.pick.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private Integer SYSTEM_ADMIN_ROLE = RoleEnum.SYSTEM_ADMIN_ROLE.getCode();

    private final NoticeRepository noticeRepository;
    private final SecurityUserDtoLoader securityUserDtoLoader;

    @Override
    public ResponseData saveNotice(NoticeSaveReqDto req) {

        Integer role = securityUserDtoLoader.getRoleAsInteger();
        Integer userCd = securityUserDtoLoader.getUserCd();

        Notice notice = new Notice();
        notice.setCategory(req.getCategory());
        notice.setNoticeTitle(req.getTitle());
        notice.setNoticeContent(req.getContent());
        notice.setWriterCd(userCd);
        notice.setActiveFlag(req.getActiveFlag());

        if (role == SYSTEM_ADMIN_ROLE) {
            return new NoticeSaveResDto(noticeRepository.saveNotice(notice));
        } else {
            return new BooleanResDto(false);
        }
    }

    @Override
    public ResponseData deleteNotice(NoticeDeleteReqDto req) {

        Integer role = securityUserDtoLoader.getRoleAsInteger();
        Integer noticeCd = req.getNoticeCd();

        if (role == SYSTEM_ADMIN_ROLE) {
            noticeRepository.deleteNotice(noticeCd);
            return new BooleanResDto(true);
        } else {
            return new BooleanResDto(false);
        }
    }

    @Override
    public ResponseData getNoticeInfo(NoticeInfoReqDto req) {

        Integer role = securityUserDtoLoader.getRoleAsInteger();
        Integer noticeCd = req.getNoticeCd();

        Integer active = noticeRepository.getActive(noticeCd);

        if (active == 1) {
            return noticeRepository.getNoticeInfo(noticeCd);
        } else {
            // 비공개일시 권한 확인
            if (role == SYSTEM_ADMIN_ROLE) {
                return noticeRepository.getNoticeInfo(noticeCd);
            } else {
                throw new IllegalArgumentException("권한 외 접근입니다.");
            }
        }
    }

    @Override
    public List<NoticeResDto> getNoticeList(NoticeReqDto req) {

        Integer role = securityUserDtoLoader.getRoleAsInteger();
        String search = "%" + req.getSearch() + "%";
        List<NoticeResDto> response = new ArrayList<>();

        if (SYSTEM_ADMIN_ROLE.equals(role)) {
            response = noticeRepository.getNoticeListAll(search);
        } else {
            response = noticeRepository.getNoticeList(search);
        }
        return response;
    }

}
