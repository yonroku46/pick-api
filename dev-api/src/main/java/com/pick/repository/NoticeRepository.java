package com.pick.repository;

import com.pick.dto.response.NoticeInfoResDto;
import com.pick.dto.response.NoticeResDto;
import com.pick.entity.Notice;

import java.util.List;

public interface NoticeRepository {

    Integer saveNotice(Notice notice);

    Integer getActive(Integer noticeCd);

    NoticeInfoResDto getNoticeInfo(Integer noticeCd);

    List<NoticeResDto> getNoticeList();

    List<NoticeResDto> getNoticeListAll();

}
