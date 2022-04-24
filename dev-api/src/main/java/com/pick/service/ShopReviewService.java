package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.ReviewDeleteReqDto;
import com.pick.dto.request.ReviewListReqDto;
import com.pick.dto.request.ReviewPostReqDto;

import java.util.List;

public interface ShopReviewService {

    public List<ResponseData> reviewList(ReviewListReqDto req);
    public ResponseData reviewPost(ReviewPostReqDto req);
    public ResponseData reviewDelete(ReviewDeleteReqDto req);

}