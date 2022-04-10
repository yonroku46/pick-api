package com.pick.service;

import com.pick.dto.request.ContactReqDto;
import com.pick.entity.Booking;
import com.pick.entity.Contact;

import java.util.List;

public interface ContactService {

    public boolean contact(ContactReqDto req);

    public List<Contact> searchAll();

}
