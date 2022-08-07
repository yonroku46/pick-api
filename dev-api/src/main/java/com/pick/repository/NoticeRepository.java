package com.pick.repository;

import com.pick.dto.response.NoticeResDto;
import com.pick.entity.Notice;

import java.util.List;

public interface NoticeRepository {

    Boolean saveNotice(Notice notice);
    Boolean getActive(Integer noticeCd);
    NoticeResDto getNotice(Integer noticeCd);
    List<NoticeResDto> getNoticeList();
    List<NoticeResDto> getNoticeListAll();

}
