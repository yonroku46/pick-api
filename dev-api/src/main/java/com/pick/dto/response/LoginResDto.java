package com.pick.dto.response;

import com.pick.dto.base.ResponseData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class LoginResDto extends ResponseData {
    private Integer userCd;
    private String userName;
    private String userEmail;
    private String userInfo;
    private String userImg;
    private Integer role;
    private String employment;

    public LoginResDto(Tuple tuple) {
        this.userCd = (Integer) tuple.get(0);
        this.userName = (String) tuple.get(1);
        this.userEmail = (String) tuple.get(2);
        this.userInfo = (String) tuple.get(3);
        this.userImg = (String) tuple.get(4);
        this.role = (Integer) tuple.get(5);
        this.employment = (String) tuple.get(6);
    }
}
