package com.pick.repository;

import com.pick.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    @Query(value = "SELECT * FROM public.m_shop", nativeQuery = true)
    List<Shop> searchAll();

}
