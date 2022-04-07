package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.LoginReqDto;
import com.pick.dto.response.LoginResDto;
import com.pick.exception.ErrorCode;
import com.pick.exception.ServiceException;
import com.pick.repository.UserRepository;
import com.pick.service.PublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Service
@RestControllerAdvice
@RequiredArgsConstructor
public class PublicServiceImpl implements PublicService {

    private final UserRepository userRepository;

    @Override
    public ResponseData login(LoginReqDto req) {
        try {
            String userEmail = req.getUserEmail();
            String userPw = req.getUserPw();
            LoginResDto response = new LoginResDto(userRepository.login(userEmail, userPw));
            return response;
        } catch(Exception exception) {
            throw new ServiceException(exception.toString(), ErrorCode.INTER_SERVER_ERROR);
        }
    }

}
