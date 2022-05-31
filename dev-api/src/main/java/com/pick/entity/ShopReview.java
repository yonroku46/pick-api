package com.pick.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "m_shop_review")
@Setter
@Getter
public class ShopReview {

    @Id
    @Column(name = "review_cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewCd;

    @Column(name = "review_reply")
    private Integer reviewReply;

    @Column(name = "user_cd")
    private Integer userCd;

    @Column(name = "shop_cd")
    private Integer shopCd;

    @Column(name = "review_text")
    private String reviewText;

    @Column(name = "review_time")
    private Timestamp reviewTime;

    @Column(name = "ratings")
    private Double ratings;

    @Column(name = "delete_flag")
    private Integer deleteFlag;
}