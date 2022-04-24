package com.pick.repository;

import com.pick.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query(value =
            "SELECT menu_cd,menu_category,menu_name,menu_description,menu_price,menu_img" +
            " FROM public.m_menu" +
            " WHERE shop_cd = :shop_cd" +
            " AND menu_cd IN :menu_cd_list"
            , nativeQuery = true)
    List<Tuple> getMenuList(@Param("shop_cd") Integer shopCd, @Param("menu_cd_list") String[] cdList);

}