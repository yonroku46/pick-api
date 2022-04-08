package com.pick.service.impl;

import com.pick.repository.ShopRequestRepository;
import com.pick.service.ShopRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopRequestServiceImpl implements ShopRequestService {

    private final ShopRequestRepository shopRequestRepository;

}
