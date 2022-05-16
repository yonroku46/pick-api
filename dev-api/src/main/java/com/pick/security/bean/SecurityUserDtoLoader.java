package com.pick.security.bean;

import com.pick.dto.response.LoginResDto;
import com.pick.security.auth.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityUserDtoLoader { // 요청자의 정보를 리턴
    public Integer getUserCd() {
        if (isAnonymous()) {
            return null;
        }
        return getLoginResDto().getUserCd();
    }

    public String getUserEmail() {
        if (isAnonymous()) {
            return null;
        }
        return getLoginResDto().getUserEmail();
    }

    public String getRoleAsString() {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities.get(0).getAuthority();
    }

    public Integer getRoleAsInteger() {
        if (isAnonymous()) {
            return null;
        }
        return getLoginResDto().getRole();
    }

    public boolean isAnonymous() {
        LoginResDto loginResDto = getLoginResDto();
        if (loginResDto == null) {
            return true;
        }
        return false;
    }

    private LoginResDto getLoginResDto() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) authentication.getPrincipal()).getLoginResDto();
        }
        return null;
    }
}
