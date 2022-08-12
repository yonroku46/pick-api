package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;
import com.pick.dto.response.NoticeResDto;

import java.util.List;

public interface NoticeService {

    ResponseData saveNotice(NoticeSaveReqDto req);
    ResponseData getNoticeInfo(NoticeInfoReqDto req);
    List<NoticeResDto> getNoticeList();

}
