package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.LoginReqDto;
import com.pick.dto.request.MyFavoritesReqDto;
import com.pick.dto.response.LoginResDto;

import java.util.List;

public interface PublicService {

    public ResponseData login(LoginReqDto req);
    public List<ResponseData> myFavorites(MyFavoritesReqDto req);

}
