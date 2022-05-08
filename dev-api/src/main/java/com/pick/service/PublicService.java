package com.pick.service;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;

import java.util.List;

public interface PublicService {

    public ResponseData login(LoginReqDto req);
    public List<ResponseData> myFavorites(MyFavoritesReqDto req);
    public ResponseData bookingCheck(BookingCheckReqDto req);
    public ResponseData mailCheck(MailCheckReqDto req);
    public ResponseData certification(CertificationReqDto req);
    public ResponseData resetPwd(ResetPwdReqDto req);
    public ResponseData mailService(MailServiceReqDto req);
    public ResponseData signup(SignupReqDto req);

}
