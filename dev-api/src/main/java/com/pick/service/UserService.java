package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.DashboardRequestConfirmReqDto;
import com.pick.dto.request.DashboardRequestListReqDto;
import com.pick.dto.request.SubmitEmploymentReqDto;
import com.pick.dto.request.UserInfoUpdateReqDto;

import java.util.List;

public interface UserService {

    public List<ResponseData> dashboardRequestList(DashboardRequestListReqDto req);
    public ResponseData userInfoUpdate(UserInfoUpdateReqDto req);
    public ResponseData submitEmployment(SubmitEmploymentReqDto req);
    public ResponseData dashboardRequestConfirm(DashboardRequestConfirmReqDto req);

}
