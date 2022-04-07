package com.pick.controller;

import com.pick.entity.Favorite;
import com.pick.entity.base.ListResponse;
import com.pick.service.FavoriteService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final ResponseService responseService;

    @GetMapping("/all")
    public ListResponse<Favorite> searchAll() {
        return responseService.getListResponse(favoriteService.searchAll());
    }
}
