package com.pick.repository;

import com.pick.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "SELECT * FROM public.m_booking", nativeQuery = true)
    List<Booking> searchAll();

}
