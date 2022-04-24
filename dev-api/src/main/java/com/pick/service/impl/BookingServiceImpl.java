package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.BookingListReqDto;
import com.pick.dto.response.BookingListResDto;
import com.pick.dto.response.LoginResDto;
import com.pick.entity.Booking;
import com.pick.entity.base.ListResponse;
import com.pick.repository.BookingRepository;
import com.pick.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

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
    public List<Booking> searchAll() {
        List<Booking> bookingList = bookingRepository.searchAll();
        return bookingList;
    }
}
