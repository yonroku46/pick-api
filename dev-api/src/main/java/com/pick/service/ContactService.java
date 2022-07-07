package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.ContactReqDto;

public interface ContactService {

    public ResponseData contact(ContactReqDto req);

}
