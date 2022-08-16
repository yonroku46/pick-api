package com.pick.controller;

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
     * 공지사항 내용 수정
     */
    @PostMapping("/edit")
    public SingleResponse editNotice(@RequestBody NoticeEditReqDto req) {
        return responseService.getSingleResponse(noticeService.editNotice(req));
    }

    /**
     * 공지사항 활성/비활성화 수정
     */
    @PostMapping("/editActive")
    public SingleResponse editActiveNotice(@RequestBody NoticeActiveEditReqDto req) {
        return responseService.getSingleResponse(noticeService.editActiveNotice(req));
    }

    /**
     * 공지사항 삭제
     */
    @PostMapping("/delete")
    public SingleResponse deleteNotice(@RequestBody NoticeDeleteReqDto req) {
        return responseService.getSingleResponse(noticeService.deleteNotice(req));
    }

    /**
     * 공지사항 내용
     */
    @GetMapping("/info")
    public SingleResponse getNoticeInfo(NoticeInfoReqDto req) {
        return responseService.getSingleResponse(noticeService.getNoticeInfo(req));
    }

    /**
     * 공지사항 목록
     */
    @GetMapping("/list")
    public ListResponse<NoticeResDto> getNoticeList(NoticeReqDto req) {
        return responseService.getListResponse(noticeService.getNoticeList(req));
    }

}
