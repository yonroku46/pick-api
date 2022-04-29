package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.BookingCheckReqDto;
import com.pick.dto.request.LoginReqDto;
import com.pick.dto.request.MyFavoritesReqDto;
import com.pick.dto.response.BooleanResDto;
import com.pick.dto.response.LoginResDto;
import com.pick.dto.response.MyFavoritesResDto;
import com.pick.repository.BookingRepository;
import com.pick.repository.UserRepository;
import com.pick.service.PublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.Tuple;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RestControllerAdvice
@RequiredArgsConstructor
public class PublicServiceImpl implements PublicService {

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

}
