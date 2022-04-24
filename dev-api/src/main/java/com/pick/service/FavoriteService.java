package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.BookingListReqDto;
import com.pick.dto.request.FavoriteListReqDto;
import com.pick.entity.Contact;
import com.pick.entity.Favorite;

import java.util.List;

public interface FavoriteService {

    public List<ResponseData> favoriteList(FavoriteListReqDto req);
    public List<Favorite> searchAll();

}
