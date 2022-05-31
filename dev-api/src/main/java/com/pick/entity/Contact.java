package com.pick.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "m_contact")
public class Contact {

    @Id
    @Column(name = "contact_cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contactCd;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "category")
    private String category;

    @Column(name = "detail")
    private String detail;

    @Column(name = "create_time")
    private Timestamp createTime;

}