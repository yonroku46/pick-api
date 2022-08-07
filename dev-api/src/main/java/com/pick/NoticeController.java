package com.pick;

import com.pick.dto.request.*;
import com.pick.dto.response.NoticeResDto;
import com.pick.entity.base.ListResponse;
import com.pick.entity.base.SingleResponse;
import com.pick.service.NoticeService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;
    private final ResponseService responseService;

    /**
     * 공지사항 저장
     */
    @PostMapping("/save")
    public SingleResponse saveNotice(@RequestBody NoticeSaveReqDto req) {
        return responseService.getSingleResponse(noticeService.saveNotice(req));
    }

    /**
     * 공지사항 내용
     */
    @GetMapping("/info")
    public SingleResponse getNotice(NoticeInfoReqDto req) {
        return responseService.getSingleResponse(noticeService.getNotice(req));
    }

    /**
     * 공지사항 목록
     */
    @GetMapping("/list")
    public ListResponse<NoticeResDto> getNoticeList() {
        return responseService.getListResponse(noticeService.getNoticeList());
    }

}
