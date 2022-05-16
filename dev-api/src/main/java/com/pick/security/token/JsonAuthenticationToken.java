package com.pick.security.token;

import com.pick.security.auth.UserDetailsImpl;
import com.pick.security.dto.SecurityLoginReqDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JsonAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public JsonAuthenticationToken(SecurityLoginReqDto securityLoginReqDto) {
        super(securityLoginReqDto.getUserEmail(), securityLoginReqDto.getUserPw());
    }

    public JsonAuthenticationToken(UserDetailsImpl userDetails) {
        super(userDetails, null, userDetails.getAuthorities());
    }
}