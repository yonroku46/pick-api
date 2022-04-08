package com.pick.controller;

import com.pick.service.ShopRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop/request")
public class ShopRequestController {

    private final ShopRequestService shopRequestService;

}
