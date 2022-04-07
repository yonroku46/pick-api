package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.entity.User;
import com.pick.exception.ErrorCode;
import com.pick.exception.ServiceException;
import com.pick.repository.UserRepository;
import com.pick.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> searchAll() {
        try {
            List<User> response = userRepository.searchAll();
            return response;
        } catch(Exception exception) {
            throw new ServiceException(exception.toString(), ErrorCode.INTER_SERVER_ERROR);
        }
    }
}
