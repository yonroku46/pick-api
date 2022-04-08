package com.pick.controller;

import com.pick.service.ShopReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop/review")
public class ShopReviewController {

    private final ShopReviewService shopReviewService;

}
