package com.pick.repository;

import com.pick.entity.Menu;
import com.pick.model.ShopInfo;
import com.pick.model.ShopMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO public.m_menu" +
            "  (shop_cd,menu_category,menu_name,menu_description,menu_price,delete_flag)" +
            " VALUES" +
            "  (:shop_cd, :#{#menu.menuCategory}, :#{#menu.menuName}, :#{#menu.menuDescription}, :#{#menu.menuPrice}, :pin)"
            , nativeQuery = true)
    int saveMenuInfo(@Param("shop_cd") Integer shopCd, @Param("menu") ShopMenu menu, @Param("pin") Integer pin);

    @Query(value =
            "SELECT menu_cd FROM public.m_menu" +
            " WHERE shop_cd = :shop_cd AND delete_flag = :pin"
            , nativeQuery = true)
    Integer getMenuCd(@Param("shop_cd") Integer shopCd, @Param("pin") Integer pin);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_menu" +
            " SET" +
            "  menu_category = :#{#menu.menuCategory}," +
            "  menu_name = :#{#menu.menuName}," +
            "  menu_description = :#{#menu.menuDescription}," +
            "  menu_price = :#{#menu.menuPrice}," +
            "  menu_img = :#{#menu.menuImg}" +
            " WHERE menu_cd = :#{#menu.menuCd}"
            , nativeQuery = true)
    int updateMenuInfo(@Param("menu") ShopMenu menu);

    @Modifying
    @Transactional
    @Query(value =
            "UPDATE public.m_menu" +
            " SET" +
            "  menu_img = :#{#menu.menuImg}," +
            "  delete_flag = 0" +
            " WHERE menu_cd = :#{#menu.menuCd}"
            , nativeQuery = true)
    int updateMenuImg(@Param("menu") ShopMenu menu);

    @Modifying
    @Transactional
    @Query(value =
            "DELETE FROM public.m_menu" +
            " WHERE shop_cd = :shop_cd AND menu_cd = :menu_cd"
            , nativeQuery = true)
    int deleteMenuInfo(@Param("menu_cd") Integer menuCd, @Param("shop_cd") Integer shopCd);

}