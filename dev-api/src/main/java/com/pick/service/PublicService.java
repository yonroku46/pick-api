package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.LoginReqDto;
import com.pick.dto.response.LoginResDto;

public interface PublicService {

    public ResponseData login(LoginReqDto req);

}
