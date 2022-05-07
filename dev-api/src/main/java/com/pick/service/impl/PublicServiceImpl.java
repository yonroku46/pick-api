package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.*;
import com.pick.dto.response.BooleanResDto;
import com.pick.dto.response.LoginResDto;
import com.pick.dto.response.MyFavoritesResDto;
import com.pick.model.EmailTO;
import com.pick.repository.BookingRepository;
import com.pick.repository.UserRepository;
import com.pick.service.EmailService;
import com.pick.service.PublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.Tuple;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RestControllerAdvice
@RequiredArgsConstructor
public class PublicServiceImpl implements PublicService {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Override
    public ResponseData login(LoginReqDto req) {
        String userEmail = req.getUserEmail();
        String userPw = req.getUserPw();
        LoginResDto response = new LoginResDto(userRepository.login(userEmail, userPw));
        return response;
    }

    @Override
    public List<ResponseData> myFavorites(MyFavoritesReqDto req) {
        Integer userCd = req.getUserCd();
        List<Tuple> tuples = userRepository.myFavorites(userCd);
        List<ResponseData> response = new ArrayList<>();
        for(Tuple tuple : tuples) {
            response.add(new MyFavoritesResDto(tuple));
        }
        return response;
    }

    @Override
    public ResponseData bookingCheck(BookingCheckReqDto req) {
        Integer userCd = req.getUserCd();
        Timestamp bookingTime = req.getBookingTime();
        BooleanResDto response = new BooleanResDto();
        int count = bookingRepository.bookingCheck(userCd, bookingTime);
        if (count == 0) {
            response.setResult(true);
        } else {
            response.setResult(false);
        }
        return response;
    }

    @Override
    public ResponseData mailCheck(MailCheckReqDto req) {
        String userEmail = req.getUserEmail();
        BooleanResDto response = new BooleanResDto();
        int cnt = userRepository.emailCount(userEmail);
        if (cnt == 1) {
            try {
                Random random = new Random();
                EmailTO email = new EmailTO();

                String pin = "";
                for (int i = 0; i < 6; i++) {
                    pin += (random.nextInt(9) + 1);
                }

                email.setReceiveMail(userEmail);
                email.setSubject("[Pick] 본인확인용 인증 안내");
                email.setMessage("본인확인용 인증 메일입니다.\n본인이 아닐 시 즉시 문의 바랍니다.\n\n인증번호 : " + pin + "\n\n(본 이메일은 발신전용이며 회신이 되지 않습니다.)");

                emailService.sendMail(email);
                userRepository.updatePin(pin, userEmail);
                response.setResult(true);
            } catch (Exception e) {
                response.setResult(false);
            }
        } else {
            response.setResult(false);
        }
        return response;
    }

    @Override
    public ResponseData certification(CertificationReqDto req) {
        String userEmail = req.getUserEmail();
        String pin = req.getPin();
        BooleanResDto response = new BooleanResDto();
        int cnt = userRepository.pinCount(pin, userEmail);
        if (cnt == 1) {
            userRepository.pinReset(userEmail);
            response.setResult(true);
        } else {
            response.setResult(false);
        }
        return response;
    }

    @Override
    public ResponseData resetPwd(ResetPwdReqDto req) {
        String userEmail = req.getUserEmail();
        String userPw = req.getUserPw();
        BooleanResDto response = new BooleanResDto();
        userRepository.resetPwd(userEmail, userPw);
        response.setResult(true);
        return response;
    }

}
