package com.pick.repository;

import com.pick.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    @Query(value =
            "SELECT" +
            "  ms.shop_cd,ms.shop_serial,ms.shop_name,ms.shop_location,ms.shop_img" +
            " FROM" +
            "  public.m_favorite mf INNER JOIN public.m_shop ms ON mf.shop_cd = ms.shop_cd" +
            " WHERE" +
            "  user_cd = :user_cd"
            , nativeQuery = true)
    List<Tuple> favoriteList(@Param("user_cd") String userCd);

}
