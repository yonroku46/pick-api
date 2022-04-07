package com.pick.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "m_favorite")
public class Favorite {

    @Id
    @Column(name = "favorite_cd")
    private Integer favoriteCd;

    @Column(name = "user_cd")
    private Integer userCd;

    @Column(name = "shop_cd")
    private Integer shopCd;

}