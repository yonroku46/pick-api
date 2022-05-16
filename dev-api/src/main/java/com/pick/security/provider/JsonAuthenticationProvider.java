package com.pick.security.provider;

import com.pick.security.auth.UserDetailsImpl;
import com.pick.security.token.JsonAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("authenticationProvider")
@RequiredArgsConstructor
public class JsonAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    // 인증 메서드
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userEmail = authentication.getName();
        String userPw = (String) authentication.getCredentials();

        // 아이디 검증
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(userEmail);
        // 비밀번호 검증
        if (!passwordEncoder.matches(userPw, userDetails.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        return new JsonAuthenticationToken(userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JsonAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
