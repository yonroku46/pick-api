package com.pick.dto.response;

import com.pick.dto.base.ResponseData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResDto extends ResponseData {
    private Integer userCd;
    private String userName;
    private String userEmail;
    private String userInfo;
    private String  userImg;
    private Integer permission;

    public LoginResDto(Tuple tuple) {
        this.userCd = (Integer) tuple.get(0);
        this.userName = (String) tuple.get(1);
        this.userEmail = (String) tuple.get(2);
        this.userInfo = (String) tuple.get(3);
        this.userImg = (String) tuple.get(4);
        this.permission = (Integer) tuple.get(5);
    }
}
