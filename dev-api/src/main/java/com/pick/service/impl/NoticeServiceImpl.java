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

    private Integer MANAGER_ROLE = RoleEnum.MANAGER_ROLE.getCode();

    private final NoticeRepository noticeRepository;
    private final SecurityUserDtoLoader securityUserDtoLoader;

    @Override
    public ResponseData saveNotice(NoticeSaveReqDto req) {

        Integer role = securityUserDtoLoader.getRoleAsInteger();

        Notice notice = new Notice();
        notice.setCategory(req.getCategory());
        notice.setNoticeTitle(req.getTitle());
        notice.setNoticeContent(req.getContent());

        BooleanResDto response = new BooleanResDto();
        if (role == MANAGER_ROLE) {
            noticeRepository.saveNotice(notice);
            response.setResult(true);
        } else {
            response.setResult(false);
        }
        return response;
    }

    @Override
    public ResponseData getNotice(NoticeInfoReqDto req) {

        Integer role = securityUserDtoLoader.getRoleAsInteger();
        Integer noticeCd = req.getNoticeCd();

        Boolean active = noticeRepository.getActive(noticeCd);

        if (active) {
            return noticeRepository.getNotice(noticeCd);
        } else {
            // 비공개일시 권한 확인
            if (role == MANAGER_ROLE) {
                return noticeRepository.getNotice(noticeCd);
            } else {
                return new BooleanResDto(false);
            }
        }
    }

    @Override
    public List<NoticeResDto> getNoticeList() {

        Integer role = securityUserDtoLoader.getRoleAsInteger();
        List<NoticeResDto> response = new ArrayList<>();

        if (role == MANAGER_ROLE) {
            response = noticeRepository.getNoticeListAll();
        } else {
            response = noticeRepository.getNoticeList();
        }
        return response;
    }

}
