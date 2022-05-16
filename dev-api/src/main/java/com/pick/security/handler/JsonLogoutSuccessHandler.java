package com.pick.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pick.entity.base.SingleResponse;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.lang.model.type.NullType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JsonLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    private final ResponseService responseService;
    private final ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SingleResponse<NullType> singleResponse = responseService.getSingleResponse(null);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), singleResponse);
    }
}
