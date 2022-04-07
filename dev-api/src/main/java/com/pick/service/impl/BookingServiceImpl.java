package com.pick.service.impl;

import com.pick.entity.Booking;
import com.pick.repository.BookingRepository;
import com.pick.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> searchAll() {
        List<Booking> bookingList = bookingRepository.searchAll();
        return bookingList;
    }
}
