package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.DashboardInfoReqDto;
import com.pick.dto.request.FavoriteReqDto;
import com.pick.dto.request.ShopInfoReqDto;
import com.pick.dto.request.ShopListReqDto;
import com.pick.entity.Shop;

import java.util.List;

public interface ShopService {

    public List<ResponseData> shopList(ShopListReqDto req);
    public ResponseData shopInfo(ShopInfoReqDto req);
    public ResponseData favorite(FavoriteReqDto req);
    public ResponseData dashboardInfo(DashboardInfoReqDto req);

}
