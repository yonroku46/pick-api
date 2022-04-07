package com.pick.service.impl;

import com.pick.entity.Contact;
import com.pick.exception.ErrorCode;
import com.pick.exception.ServiceException;
import com.pick.repository.ContactRepository;
import com.pick.service.ContactService;
import com.pick.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> searchAll() {
        try {
            List<Contact> response = contactRepository.searchAll();
            return response;
        } catch(Exception exception) {
            throw new ServiceException(exception.toString(), ErrorCode.INTER_SERVER_ERROR);
        }
    }
}
