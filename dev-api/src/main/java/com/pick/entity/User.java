package com.pick.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.pick.dto.base.ResponseData;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name = "m_user")
public class User extends ResponseData {

    @Id
    @Column(name = "user_cd")
    private Integer userCd;

    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_info")
    private String userInfo;

    @Column(name = "user_img")
    private String userImg;

    @Column(name = "additional", columnDefinition = "json")
    @JsonRawValue
    private String additional;

    @Column(name = "access_time")
    private Timestamp accessTime;

    @Column(name = "role")
    private Integer role;

    @Column(name = "pin")
    private Integer pin;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

}