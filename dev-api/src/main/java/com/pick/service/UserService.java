package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.LoginReqDto;
import com.pick.entity.User;

import java.util.List;

public interface UserService {

    public List<User> searchAll();

}
