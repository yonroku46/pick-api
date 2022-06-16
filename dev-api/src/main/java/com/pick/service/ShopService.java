package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;

import java.util.List;

public interface ShopService {

    public List<ResponseData> shopList(ShopListReqDto req);
    public ResponseData shopInfo(ShopInfoReqDto req);
    public ResponseData favorite(FavoriteReqDto req);
    public ResponseData dashboardInfo(DashboardInfoReqDto req);
    public List<ResponseData> search(ShopSearchReqDto req);
    public ResponseData saveInfo(DashboardSaveInfoReqDto req);
    public ResponseData tmpClear(DashboardTmpClearReqDto req);
    public List<ResponseData> eventShopList();
    public List<ResponseData> nearShopList();
}
