package com.pick.controller;

import com.pick.service.FavoriteService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final ResponseService responseService;

}
