package com.pick.repository;

import com.pick.entity.ShopReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.List;

public interface ShopReviewRepository extends JpaRepository<ShopReview, Integer> {

    @Query(value =
            "SELECT" +
            "  msr.review_cd,msr.review_reply,msr.user_cd,mu.user_name,mu.user_img,msr.review_text,msr.review_time,msr.ratings,msr.delete_flag" +
            " FROM" +
            "  public.m_shop_review msr INNER JOIN public.m_user mu ON msr.user_cd = mu.user_cd" +
            " WHERE" +
            "  msr.shop_cd = :shop_cd" +
            " ORDER BY msr.review_time DESC"
            , nativeQuery = true)
    List<Tuple> reviewList(@Param("shop_cd") String shopCd);

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO public.m_shop_review" +
            "  (user_cd,shop_cd,review_reply,review_text,ratings,review_time)" +
            " VALUES" +
            "  (:user_cd,:shop_cd,:review_reply,:review_text,:ratings,now())"
            , nativeQuery = true)
    int reviewPost(@Param("user_cd") Integer userCd, @Param("shop_cd") Integer shopCd, @Param("review_reply") Integer reviewReply, @Param("review_text") String reviewText, @Param("ratings") Integer ratings);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_shop_review" +
            " SET delete_flag = '1'" +
            " WHERE user_cd = :user_cd AND shop_cd = :shop_cd AND review_cd = :review_cd"
            , nativeQuery = true)
    int reviewDelete(@Param("user_cd") Integer userCd, @Param("shop_cd") Integer shopCd, @Param("review_cd") Integer reviewCd);

}
