package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;
import com.pick.dto.response.BooleanResDto;
import com.pick.dto.response.DashboardInfoResDto;
import com.pick.dto.response.ShopInfoResDto;
import com.pick.dto.response.ShopListResDto;
import com.pick.entity.Shop;
import com.pick.model.ShopImg;
import com.pick.model.ShopInfo;
import com.pick.model.ShopMenu;
import com.pick.model.ShopStaff;
import com.pick.repository.MenuRepository;
import com.pick.repository.ShopRepository;
import com.pick.repository.UserRepository;
import com.pick.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private Integer MANAGER_ROLE = 3;

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    @Override
    public List<ResponseData> shopList(ShopListReqDto req) {
        String category = req.getCategory();
        List<Tuple> tuples = shopRepository.shopList(convertCategory(category));
        List<ResponseData> response = new ArrayList<>();
        for (Tuple tuple : tuples) {
            response.add(new ShopListResDto(tuple));
        }
        return response;
    }

    @Override
    public ResponseData shopInfo(ShopInfoReqDto req) {
        Integer shopCd = req.getShopCd();
        ShopInfoResDto response = new ShopInfoResDto(shopRepository.shopInfo(shopCd));
        if (response.getStaffCdList() != null) {
            response.setStaffList(convertStaffList(response.getStaffCdList()));
        }
        if (response.getMenuCdList() != null) {
            response.setMenuList(convertMenuList(response.getShopCd(), response.getMenuCdList()));
            response.setMenuCategories(convertMenuCategories(response.getMenuList()));
        }
        return response;
    }

    @Override
    public ResponseData favorite(FavoriteReqDto req) {
        Integer userCd = req.getUserCd();
        Integer shopCd = req.getShopCd();
        Boolean isFavorite = req.getIsFavorite();
        BooleanResDto response = new BooleanResDto();
        if (isFavorite) {
            shopRepository.delFavorite(userCd, shopCd);
            response.setResult(false);
        } else {
            shopRepository.insFavorite(userCd, shopCd);
            response.setResult(true);
        }
        return response;
    }

    @Override
    public ResponseData dashboardInfo(DashboardInfoReqDto req) {
        Integer shopCd = req.getShopCd();
        Integer role = req.getRole();
        if (role == MANAGER_ROLE) {
            DashboardInfoResDto response = new DashboardInfoResDto(shopRepository.dashboardInfo(shopCd));
            if (response.getStaffCdList() != null) {
                response.setStaffList(convertStaffList(response.getStaffCdList()));
            }
            if (response.getMenuCdList() != null) {
                response.setMenuList(convertMenuList(response.getShopCd(), response.getMenuCdList()));
                response.setMenuCategories(convertMenuCategories(response.getMenuList()));
            }
            return response;
        } else {
            return null;
        }
    }

    @Override
    public List<ResponseData> search(ShopSearchReqDto req) {
        String category = req.getCategory();
        String value = req.getValue();
        List<Tuple> tuples = shopRepository.search(convertCategory(category), value);
        List<ResponseData> response = new ArrayList<>();
        for (Tuple tuple : tuples) {
            response.add(new ShopListResDto(tuple));
        }
        return response;
    }


    protected String convertCategory(String category) {
        String res = null;
        if (category.contains("hairshop")) {
            res = "HS";
        } else if (category.contains("restaurant")) {
            res = "RT";
        } else if (category.contains("cafe")) {
            res = "CF";
        }
        return res;
    }

    protected List<ShopStaff> convertStaffList(String staffCdList) {
        String[] cdList = staffCdList.split(",");
        List<Tuple> tuples = userRepository.getStaffList(cdList);
        List<ShopStaff> res = new ArrayList<>();
        for(Tuple tuple : tuples) {
            res.add(new ShopStaff(tuple));
        }
        return res;
    }

    protected List<ShopMenu> convertMenuList(Integer shopCd, String menuCdList) {
        String[]  cdList = menuCdList.split(",");
        List<Tuple> tuples = menuRepository.getMenuList(shopCd, cdList);
        List<ShopMenu> res = new ArrayList<>();
        for(Tuple tuple : tuples) {
            res.add(new ShopMenu(tuple));
        }
        return res;
    }

    protected List<String> convertMenuCategories(List<ShopMenu> menuList) {
        List<String> res = new ArrayList<>();
        for (ShopMenu menu : menuList) {
            res.add(menu.getMenuCategory());
        }
        return res.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public ResponseData saveInfo(DashboardSaveInfoReqDto req) {
        ShopInfo shop = req.getShop();
        ShopInfo shopOrigin = req.getShopOrigin();
        BooleanResDto response = new BooleanResDto();
        try {
            if (shop.equals(shopOrigin)) {
                response.setResult(true);
            } else {
                // shopInfo tmp에있는 이미지 파일들 샵폴더로 이동
                String publicPath = "C:/git/pick-viewer/dev-viewer/public/";
                String imgPath = "images/";
                List<ShopImg> shopImgList = shop.getShopImg();
//                for (ShopImg img : shopImgList) {
//                    System.out.println(img);
//                }
                shopRepository.saveInfo(shop);

                // staffInfo
                // staffInfo - update
                // staffInfo - delete

                // menuInfo
                // menuInfo - update
                // menuInfo - delete
                response.setResult(true);
            }
        } catch (Exception err) {
            response.setResult(false);
        }
        return response;
    }
}
