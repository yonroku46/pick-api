package com.pick.service.impl;

import com.pick.repository.ShopReviewRepository;
import com.pick.service.ShopReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopReviewServiceImpl implements ShopReviewService {

    private final ShopReviewRepository shopReviewRepository;

}
