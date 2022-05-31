package com.pick.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "m_menu")
public class Menu {

    @Id
    @Column(name = "menu_cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuCd;

    @Column(name = "shop_cd")
    private String shopCd;

    @Column(name = "menu_category")
    private String menuCategory;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_description")
    private String menuDescription;

    @Column(name = "menu_price")
    private Integer menuPrice;

    @Column(name = "menu_img")
    private String menuImg;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

}