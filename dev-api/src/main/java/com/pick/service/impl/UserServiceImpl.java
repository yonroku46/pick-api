package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.DashboardRequestConfirmReqDto;
import com.pick.dto.request.DashboardRequestListReqDto;
import com.pick.dto.request.SubmitEmploymentReqDto;
import com.pick.dto.request.UserInfoUpdateReqDto;
import com.pick.dto.response.BooleanResDto;
import com.pick.dto.response.DashboardRequestListResDto;
import com.pick.dto.response.ShopListResDto;
import com.pick.entity.User;
import com.pick.exception.ErrorCode;
import com.pick.exception.ServiceException;
import com.pick.model.ShopStaff;
import com.pick.repository.ShopRepository;
import com.pick.repository.ShopRequestRepository;
import com.pick.repository.UserRepository;
import com.pick.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private Integer STAFF_ROLE = 2;
    private Integer MANAGER_ROLE = 3;
    private Integer REQUEST_ACCEPT = 1;
    private Integer REQUEST_REFUSE = 2;

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final ShopRequestRepository shopRequestRepository;

    @Override
    public List<ResponseData> dashboardRequestList(DashboardRequestListReqDto req) {
        Integer shopCd = req.getShopCd();
        Integer role = req.getRole();
        if (role == MANAGER_ROLE) {
            List<Tuple> tuples = userRepository.dashboardRequestList(shopCd);
            List<ResponseData> response = new ArrayList<>();
            for (Tuple tuple : tuples) {
                response.add(new DashboardRequestListResDto(tuple));
            }
            return response;
        } else {
            return null;
        }
    }

    @Override
    public ResponseData userInfoUpdate(UserInfoUpdateReqDto req) {
        Integer userCd = req.getUserCd();
        String userInfo = req.getUserInfo();
        BooleanResDto response = new BooleanResDto();
        userRepository.userInfoUpdate(userCd, userInfo);
        response.setResult(true);
        return response;
    }

    @Override
    public ResponseData submitEmployment(SubmitEmploymentReqDto req) {
        Integer userCd = req.getUserCd();
        String shopSerial = req.getShopSerial();
        Integer role = req.getRole();
        if (role == STAFF_ROLE) {
            BooleanResDto response = new BooleanResDto();
            // 매장 시리얼번호로 매장코드 획득
            Integer shopCd = shopRepository.getBySerial(shopSerial);
            // 존재하지 않는 시리얼넘버일시 잘못된 코드 입력
            if (shopCd == null) {
                response.setResult(false);
                return response;
            }
            // 매장 및 유저 정보 업데이트
            shopRepository.submitEmployment(userCd, shopCd);
            userRepository.submitEmployment(userCd, shopCd);
            response.setResult(true);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public ResponseData dashboardRequestConfirm(DashboardRequestConfirmReqDto req) {
        Integer userCd = req.getUserCd();
        Integer shopCd = req.getShopCd();
        Integer requestCd = req.getRequestCd();
        Integer requestStat = req.getRequestStat();
        BooleanResDto response = new BooleanResDto();
        if (requestStat == REQUEST_ACCEPT) {
            // 매장 및 유저 정보 업데이트
            userRepository.updateEmployment(userCd);
            shopRepository.addStaff(userCd, shopCd);
            response.setResult(true);
        } else if (requestStat == REQUEST_REFUSE) {
            // 유저정보에서 리퀘스트정보 삭제
            userRepository.deleteEmployment(userCd);
            response.setResult(true);
        } else {
            return null;
        }
        shopRequestRepository.updateStat(requestCd, requestStat);
        return response;
    }
}
