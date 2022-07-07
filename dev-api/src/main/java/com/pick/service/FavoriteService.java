package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.FavoriteListReqDto;

import java.util.List;

public interface FavoriteService {

    public List<ResponseData> favoriteList(FavoriteListReqDto req);

}
