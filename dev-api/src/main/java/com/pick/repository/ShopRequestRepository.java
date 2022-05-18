package com.pick.repository;

import com.pick.entity.ShopRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;

public interface ShopRequestRepository extends JpaRepository<ShopRequest, Integer> {

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_shop_request" +
            " SET request_stat = :request_stat" +
            " WHERE request_cd = :request_cd"
            , nativeQuery = true)
    int updateStat(@Param("request_cd") Integer requestCd, @Param("request_stat") Integer requestStat);

}
