package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.BookingInfoReqDto;
import com.pick.dto.request.BookingListReqDto;
import com.pick.dto.request.BookingReqDto;
import com.pick.dto.request.DashboardBookingListReqDto;
import com.pick.dto.response.BookingInfoResDto;
import com.pick.dto.response.BookingListResDto;
import com.pick.dto.response.BooleanResDto;
import com.pick.model.BookingDetail;
import com.pick.model.Order;
import com.pick.repository.BookingRepository;
import com.pick.repository.ShopRepository;
import com.pick.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private String HAIRSHOP = "hairshop";
    private String RESTAURANT = "restaurant";
    private String CAFE = "cafe";

    private Integer MANAGER_ROLE = 3;

    private final BookingRepository bookingRepository;
    private final ShopRepository shopRepository;

    @Override
    public List<ResponseData> bookingList(BookingListReqDto req) {
        String userCd = req.getUserCd();
        List<Tuple> tuples = bookingRepository.bookingList(userCd);
        List<ResponseData> response = new ArrayList<>();
        for(Tuple tuple : tuples) {
            response.add(new BookingListResDto(tuple));
        }
        return response;
    }

    @Override
    public ResponseData booking(BookingReqDto req) {
        Integer userCd = req.getUserCd();
        Integer shopCd = req.getShopCd();
        Timestamp bookingTime = req.getBookingTime();
        Integer bookingPrice = req.getBookingPrice();
        String category = req.getCategory();

        BookingDetail bookingDetail = req.getBookingDetail();
        Integer designer = bookingDetail.getDesigner();
        Integer style = bookingDetail.getStyle();
        Integer customers = bookingDetail.getCustomers();
        Integer discount = bookingDetail.getDiscount();
        String orders = convertOrder(bookingDetail.getOrders());

        BooleanResDto response = new BooleanResDto();

        if (category.equals(HAIRSHOP)) {
            bookingRepository.bookingHairshop(userCd, shopCd, designer, category, bookingTime, bookingPrice,
                    designer, style, discount);
            response.setResult(true);
        } else if (category.equals(RESTAURANT)) {
            Integer managerCd = shopRepository.getShopManager(shopCd);
            bookingRepository.bookingRestaurant(userCd, shopCd, managerCd, category,bookingTime, bookingPrice,
                    customers, orders, discount);
            response.setResult(true);
        } else if (category.equals(CAFE)) {
            Integer managerCd = shopRepository.getShopManager(shopCd);
            bookingRepository.bookingCafe(userCd, shopCd, managerCd, category, bookingTime, bookingPrice,
                    customers, orders, discount);
            response.setResult(true);
        } else {
            return null;
        }
        return response;
    }

    @Override
    public List<ResponseData> dashboardBookingList(DashboardBookingListReqDto req) {
        Integer shopCd = req.getShopCd();
        Integer role = req.getRole();
        if (role == MANAGER_ROLE) {
            List<Tuple> tuples = bookingRepository.dashboardBookingList(shopCd);
            List<ResponseData> response = new ArrayList<>();
            for (Tuple tuple : tuples) {
                response.add(new BookingListResDto(tuple));
            }
            return response;
        } else {
            return null;
        }
    }

    @Override
    public ResponseData bookingInfo(BookingInfoReqDto req) {
        Integer userCd = req.getUserCd();
        Integer bookingCd = req.getBookingCd();
        BookingInfoResDto response = new BookingInfoResDto(bookingRepository.getBookingInfo(userCd, bookingCd));

        return response;
    }

    private String convertOrder(List<Order> orders) {
        if (orders == null) {
            return null;
        } else {
            String result = "";
            for (Order order : orders) {
                result += String.valueOf(order.getMenuCd()) + "&" + String.valueOf(order.getNum()) + ",";
            }
            return result.substring(0, result.length() - 1);
        }
    }
}
