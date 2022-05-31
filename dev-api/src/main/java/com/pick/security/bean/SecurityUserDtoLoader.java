package com.pick.security.bean;

import com.pick.dto.response.LoginResDto;
import com.pick.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUserDtoLoader { // 요청자의 정보를 리턴
    private final RoleConverter roleConverter;

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
        if (isAnonymous()) {
            return "ANONYMOUS";
        }
        Integer roleInt = getLoginResDto().getRole();
        return roleConverter.convertToString(roleInt);
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
