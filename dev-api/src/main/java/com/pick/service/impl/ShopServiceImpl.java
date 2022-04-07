package com.pick.service.impl;

import com.pick.entity.Shop;
import com.pick.repository.ShopRepository;
import com.pick.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    @Override
    public List<Shop> searchAll() {
        List<Shop> shopList = shopRepository.searchAll();
        return shopList;
    }
}
