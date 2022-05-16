package com.pick.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pick.dto.response.LoginResDto;
import com.pick.security.dto.SecurityAdditionalDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserDetailsImpl extends User {
    private LoginResDto loginResDto;

    public UserDetailsImpl(com.pick.entity.User user, List<GrantedAuthority> roles) {
        super(user.getUserEmail(), user.getUserPw(), roles);
        loginResDto = new LoginResDto();
        loginResDto.setUserCd(user.getUserCd());
        loginResDto.setUserEmail(user.getUserEmail());
        loginResDto.setUserName(user.getUserName());
        loginResDto.setUserInfo(user.getUserInfo());
        loginResDto.setUserImg(user.getUserImg());
        loginResDto.setRole(user.getRole());

        String additional = user.getAdditional();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SecurityAdditionalDto additionalDto = objectMapper.readValue(additional, SecurityAdditionalDto.class);
            loginResDto.setEmployment(additionalDto.getEmployment());
        } catch (Exception e) {
        }
    }
}
