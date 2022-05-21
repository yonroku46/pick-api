package com.pick.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pick.dto.response.LoginResDto;
import com.pick.entity.base.SingleResponse;
import com.pick.repository.UserRepository;
import com.pick.security.auth.UserDetailsImpl;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ResponseService responseService;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Override
    @ResponseBody
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginResDto loginResDto = ((UserDetailsImpl) authentication.getPrincipal()).getLoginResDto();

        // 로그인 시간 update
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        userRepository.updateAccessTime(loginResDto.getUserCd(), currentTime);

        SingleResponse<LoginResDto> singleResponse = responseService.getSingleResponse(loginResDto);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), singleResponse);
    }
}
