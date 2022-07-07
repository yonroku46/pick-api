package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;
import com.pick.dto.response.*;
import com.pick.model.ShopImg;
import com.pick.model.ShopInfo;
import com.pick.model.ShopMenu;
import com.pick.model.ShopStaff;
import com.pick.repository.MenuRepository;
import com.pick.repository.ShopRepository;
import com.pick.repository.UserRepository;
import com.pick.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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

    private final String publicPath = "C:/git/pick-viewer/dev-viewer/public/";
//    private final String publicPath = "/home/dev-service/dev-viewer/public";

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
                // 샵정보 처리
                List<String> staffCdList = new ArrayList<>();
                List<String> menuCdList = new ArrayList<>();
                List<ShopImg> shopImgList = shop.getShopImg();
                String shopImg = "";
                for (ShopImg img : shopImgList) {
                    if (img.getImgPath().contains("tmp")) {
                        File file = new File(publicPath + img.getImgPath());
                        String copyImgPath = "images/shop/" + shop.getShopCd() + "/" + img.getId() + ".png";
                        File copyFile = new File(publicPath + copyImgPath);
                        Files.copy(file.toPath(), copyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    shopImg += "images/shop/" + shop.getShopCd() + "/" + img.getId() + ".png,";
                }
                shopImg = shopImg.substring(0, shopImg.length() - 1);
                clearTmpPath(publicPath + "images/shop/" + shop.getShopCd() + "/tmp");

                // 샵 스태프정보 처리
                List<ShopStaff> staffList = shop.getStaffList();
                List<ShopStaff> originStaffList = shopOrigin.getStaffList();
                for (ShopStaff originStaff : originStaffList) {
                    if (staffList.stream().anyMatch(staff -> staff.getUserCd() == originStaff.getUserCd())) {
                        // staffInfo - update
                        ShopStaff targetStaff = staffList.stream().filter(staff -> staff.getUserCd() == originStaff.getUserCd()).findFirst().get();
                        if (!targetStaff.equals(originStaff)) {
                            userRepository.updateStaffInfo(targetStaff);
                        }
                        staffCdList.add(targetStaff.getUserCd().toString());
                    } else {
                        // staffInfo - delete
                        ShopStaff targetStaff = originStaffList.stream().filter(staff -> staff.getUserCd() == originStaff.getUserCd()).findFirst().get();
                        userRepository.deleteStaffInfo(targetStaff);
                    }
                }

                // 샵 메뉴정보 처리
                List<ShopMenu> menuList = shop.getMenuList();
                List<ShopMenu> originMenuList = shopOrigin.getMenuList();
                for (ShopMenu menu : menuList) {
                    if (menu.getNewFlag()) {
                        // menuInfo - new insert
                        Integer pin = menu.getMenuCd();
                        menuRepository.saveMenuInfo(shop.getShopCd(), menu, pin);
                        menu.setMenuCd(menuRepository.getMenuCd(shop.getShopCd(), pin));

                        File file = new File(publicPath + menu.getMenuImg());
                        String copyImgPath = "images/menu/" + shop.getShopCd() + "/" + menu.getMenuCd() + ".png";
                        File copyFile = new File(publicPath + copyImgPath);
                        Files.copy(file.toPath(), copyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        menu.setMenuImg(copyImgPath);

                        menuRepository.updateMenuImg(menu);
                    } else {
                        // menuInfo - update
                        ShopMenu targetMenu = originMenuList.stream().filter(originMenu -> originMenu.getMenuCd() == menu.getMenuCd()).findFirst().get();
                        if (!targetMenu.equals(menu)) {
                            if (!targetMenu.getMenuImg().equals(menu.getMenuImg())) {
                                File file = new File(publicPath + menu.getMenuImg());
                                String copyImgPath = "images/menu/" + shop.getShopCd() + "/" + menu.getMenuCd() + ".png";
                                File copyFile = new File(publicPath + copyImgPath);
                                Files.copy(file.toPath(), copyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                menu.setMenuImg(copyImgPath);
                            }
                            menuRepository.updateMenuInfo(menu);
                        }
                    }
                    menuCdList.add(menu.getMenuCd().toString());
                }
                for (ShopMenu originMenu : originMenuList) {
                    // menuInfo - delete
                    if (!menuList.stream().anyMatch(menu -> menu.getMenuCd() == originMenu.getMenuCd())) {
                        File file = new File(publicPath + originMenu.getMenuImg());
                        Files.delete(file.toPath());
                        menuRepository.deleteMenuInfo(originMenu.getMenuCd(), shop.getShopCd());
                    }
                }
                clearTmpPath(publicPath + "images/menu/" + shop.getShopCd() + "/tmp");

                String staffSet = StringUtils.join(staffCdList, ',');
                String menuSet = StringUtils.join(menuCdList, ',');
                shopRepository.saveInfo(shop, shopImg, staffSet, menuSet);
                response.setResult(true);
            }
        } catch (Exception err) {
            response.setResult(false);
        }
        return response;
    }

    @Override
    public ResponseData tmpClear(DashboardTmpClearReqDto req) {
        Integer shopCd = req.getShopCd();
        Integer role = req.getRole();
        BooleanResDto response = new BooleanResDto();
        if (role == MANAGER_ROLE) {
            clearTmpPath(publicPath + "images/shop/" + shopCd + "/tmp");
            clearTmpPath(publicPath + "images/menu/" + shopCd + "/tmp");
            response.setResult(true);
        } else {
            response.setResult(false);
        }
        return response;
    }

    @Override
    public List<ResponseData> eventShopList() {
        List<Tuple> tuples = shopRepository.getEventShopList();
        List<ResponseData> response = new ArrayList<>();
        for (Tuple tuple : tuples) {
            response.add(new HomesShopListResDto(tuple));
        }
        return response;
    }

    @Override
    public List<ResponseData> nearShopList() {
        // 유저의 위치정보
        String location = "서울";
        // 현재 시간+요일에 영업중인 가게만 획득하도록 변경
        List<Tuple> tuples = shopRepository.getNearShopList(location);
        List<ResponseData> response = new ArrayList<>();
        for (Tuple tuple : tuples) {
            response.add(new HomesShopListResDto(tuple));
        }
        return response;
    }

    private void clearTmpPath(String clearPath) {
        try {
            File rootDir = new File(clearPath);
            File[] allFiles = rootDir.listFiles();
            if (allFiles != null) {
                for (File file : allFiles) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
