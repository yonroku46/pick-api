package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.ReviewDeleteReqDto;
import com.pick.dto.request.ReviewListReqDto;
import com.pick.dto.request.ReviewPostReqDto;
import com.pick.dto.response.BooleanResDto;
import com.pick.repository.ShopReviewRepository;
import com.pick.service.ShopReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ShopReviewServiceImpl implements ShopReviewService {

    private int DELETE_FLG = 1;
    private String DELETE_MESSAGE = "삭제된 리뷰입니다.";

    private final ShopReviewRepository shopReviewRepository;

    @Override
    public List<ResponseData> reviewList(ReviewListReqDto req) {
        String shopCd = req.getShopCd();
        List<Tuple> tuples = shopReviewRepository.reviewList(shopCd);
        List<ResponseData> response = new ArrayList<>();

        // 일반・삭제댓글처리
        for (Tuple tuple : tuples) {
            com.pick.dto.response.ReviewListResDto review = new com.pick.dto.response.ReviewListResDto(tuple);
            if (review.getDeleteFlag() == DELETE_FLG && review.getReviewReply() != null) {
                review.setUserCd(null);
                review.setUserName(null);
                review.setUserImg(null);
                review.setReviewReply(null);
                review.setReviewText(DELETE_MESSAGE);
            }
            response.add(review);
        }

        // 대댓글 리스트 처리
        Iterator<ResponseData> r = response.iterator();
        Map<Integer, List<com.pick.dto.response.ReviewListResDto>> replyMap = new HashMap<>();

        while (r.hasNext()) {
            com.pick.dto.response.ReviewListResDto review = (com.pick.dto.response.ReviewListResDto) r.next();
            Integer target = review.getReviewReply();
            if (target != null) {
                List<com.pick.dto.response.ReviewListResDto> list = replyMap.get(target);
                if (replyMap.get(target) == null) {
                    list = new ArrayList<>();
                }
                list.add(review);
                replyMap.put(target, list);
                r.remove();
            }
        }

        replyMap.forEach((k, v) -> {
            for (ResponseData data: response) {
                com.pick.dto.response.ReviewListResDto review = (com.pick.dto.response.ReviewListResDto) data;
                if (review.getReviewCd() == k && review.getDeleteFlag() != DELETE_FLG) {
                    review.setReplyList(v);
                }
            }
        });

        return response;
    }

    @Override
    public ResponseData reviewPost(ReviewPostReqDto req) {
        Boolean isStaff = req.getIsStaff();
        Integer userCd = req.getUserCd();
        Integer shopCd = req.getShopCd();
        Integer reviewReply = null;
        String reviewText = req.getReviewText();
        Integer ratings = req.getRatings();
        BooleanResDto response = new BooleanResDto();
        if (isStaff) {
            reviewReply = req.getReviewReply();
        }
        shopReviewRepository.reviewPost(userCd, shopCd, reviewReply, reviewText, ratings);
        response.setResult(true);
        return response;
    }

    @Override
    public ResponseData reviewDelete(ReviewDeleteReqDto req) {
        Integer userCd = req.getUserCd();
        Integer shopCd = req.getShopCd();
        Integer reviewCd = req.getReviewCd();
        BooleanResDto response = new BooleanResDto();
        shopReviewRepository.reviewDelete(userCd, shopCd, reviewCd);
        response.setResult(true);
        return response;
    }

}
