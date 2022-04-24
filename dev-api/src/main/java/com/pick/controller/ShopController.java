package com.pick.controller;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.FavoriteReqDto;
import com.pick.dto.request.MyFavoritesReqDto;
import com.pick.dto.request.ShopInfoReqDto;
import com.pick.dto.request.ShopListReqDto;
import com.pick.entity.Shop;
import com.pick.entity.base.ListResponse;
import com.pick.entity.base.SingleResponse;
import com.pick.service.ShopService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;
    private final ResponseService responseService;

    /**
     * 해당 카테고리의 매장 리스트
     */
    @GetMapping("/list")
    public ListResponse<ResponseData> shopList(ShopListReqDto req) {
        return responseService.getListResponse(shopService.shopList(req));
    }

    /**
     * 해당 매장의 상세정보
     */
    @GetMapping("/info")
    public SingleResponse<ResponseData> shopInfo(ShopInfoReqDto req) {
        return responseService.getSingleResponse(shopService.shopInfo(req));
    }

    /**
     * 해당 매장 즐겨찾기 추가/제거
     */
    @PostMapping("/favorite")
    public SingleResponse<ResponseData> favorite(@RequestBody FavoriteReqDto req) {
        return responseService.getSingleResponse(shopService.favorite(req));
    }

}
