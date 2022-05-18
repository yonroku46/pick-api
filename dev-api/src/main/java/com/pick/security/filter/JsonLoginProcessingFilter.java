package com.pick.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pick.repository.UserRepository;
import com.pick.security.dto.SecurityLoginReqDto;
import com.pick.security.token.JsonAuthenticationToken;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private ObjectMapper objectMapper;
    private UserRepository userRepository;

    public JsonLoginProcessingFilter(String processingUrl) {
        super(new AntPathRequestMatcher(processingUrl, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!isJson(request) || !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("JSON 또는 POST 요청이 아닙니다.");
        }
        // request 파라미터 검증
        SecurityLoginReqDto securityLoginReqDto = objectMapper.readValue(request.getReader(), SecurityLoginReqDto.class);
        if (!StringUtils.hasText(securityLoginReqDto.getUserEmail()) || !StringUtils.hasText(securityLoginReqDto.getUserPw())) {
            throw new AuthenticationServiceException("이메일 또는 비밀번호가 공백입니다.");
        }
        // 임시토큰 생성
        JsonAuthenticationToken token = new JsonAuthenticationToken(securityLoginReqDto);

        return getAuthenticationManager().authenticate(token);
    }

    private boolean isJson(HttpServletRequest request) {
        String requestType = request.getHeader("Content-Type");
        if (MediaType.APPLICATION_JSON_UTF8_VALUE.equals(requestType) ||
                MediaType.APPLICATION_JSON_VALUE.equals(requestType)) {
            return true;
        }
        return false;
    }

    public void setObjectMapper(ObjectMapper mapper) {
        this.objectMapper = mapper;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
