package com.pick.repository;

import com.pick.dto.response.NoticeInfoResDto;
import com.pick.dto.response.NoticeResDto;
import com.pick.entity.Notice;

import java.util.List;

public interface NoticeRepository {

    Integer saveNotice(Notice notice);

    void deleteNotice(Integer noticeCd);

    Integer getActive(Integer noticeCd);

    NoticeInfoResDto getNoticeInfo(Integer noticeCd);

    List<NoticeResDto> getNoticeList(String search);

    List<NoticeResDto> getNoticeListAll(String search);

}
