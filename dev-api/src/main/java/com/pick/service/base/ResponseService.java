package com.pick.service.base;

import com.pick.entity.base.CommonResponse;
import com.pick.entity.base.ListResponse;
import com.pick.entity.base.SingleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public<T> SingleResponse<T> getSingleResponse(T data) {
        SingleResponse singleResponse = new SingleResponse();
        singleResponse.setData(data);
        setSuccessResponse(singleResponse);

        return singleResponse;
    }

    public<T> ListResponse<T> getListResponse(List<T> dataList) {
        ListResponse listResponse = new ListResponse();
        listResponse.setDataList(dataList);
        setSuccessResponse(listResponse);

        return listResponse;
    }

    void setSuccessResponse(CommonResponse response) {
        response.setCode(0);
        response.setSuccess(true);
        response.setMessage("SUCCESS");
    }

    public CommonResponse getErrorResponse(String message, int code) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

}
