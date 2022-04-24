package com.pick.controller;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.ReviewDeleteReqDto;
import com.pick.dto.request.ReviewListReqDto;
import com.pick.dto.request.ReviewPostReqDto;
import com.pick.entity.base.ListResponse;
import com.pick.entity.base.SingleResponse;
import com.pick.service.ShopReviewService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop/review")
public class ShopReviewController {

    private final ShopReviewService shopReviewService;
    private final ResponseService responseService;

    /**
     * 해당 매장의 리뷰 리스트
     */
    @GetMapping("/list")
    public ListResponse<ResponseData> reviewList(ReviewListReqDto req) {
        return responseService.getListResponse(shopReviewService.reviewList(req));
    }

    /**
     * 리뷰작성
     */
    @PostMapping("/post")
    public SingleResponse<ResponseData> reviewPost(@RequestBody ReviewPostReqDto req) {
        return responseService.getSingleResponse(shopReviewService.reviewPost(req));
    }

    /**
     * 리뷰삭제
     */
    @PostMapping("/delete")
    public SingleResponse<ResponseData> reviewDelete(@RequestBody ReviewDeleteReqDto req) {
        return responseService.getSingleResponse(shopReviewService.reviewDelete(req));
    }

}
