package com.pick.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "m_favorite")
public class Favorite {

    @Id
    @Column(name = "favorite_cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteCd;

    @Column(name = "user_cd")
    private Integer userCd;

    @Column(name = "shop_cd")
    private Integer shopCd;

}