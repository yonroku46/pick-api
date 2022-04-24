package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewListResDto extends ResponseData {
    private Integer reviewCd;
    private Integer reviewReply;
    private Integer userCd;
    private String userName;
    private String userImg;
    private String reviewText;
    private Timestamp reviewTime;
    private Float ratings;
    private Integer deleteFlag;
    private List<ReviewListResDto> replyList;

    public ReviewListResDto(Tuple tuple) {
        this.reviewCd = (Integer) tuple.get(0);
        this.reviewReply = (Integer) tuple.get(1);
        this.userCd = (Integer) tuple.get(2);
        this.userName = (String) tuple.get(3);
        this.userImg = (String) tuple.get(4);
        this.reviewText = (String) tuple.get(5);
        this.reviewTime = (Timestamp) tuple.get(6);
        this.ratings = (Float) tuple.get(7);
        this.deleteFlag = (Integer) tuple.get(8);
        this.replyList = new ArrayList<>();
    }
}
