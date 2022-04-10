package com.pick.service.impl;

import com.pick.dto.request.ContactReqDto;
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
    public boolean contact(ContactReqDto req) {
        try {
            Contact contact = new Contact();
            contact.setName(req.getUserName());
            contact.setEmail(req.getUserEmail());
            contact.setCategory(req.getCategory());
            contact.setDetail(req.getDetail());
            Contact response = contactRepository.save(contact);
            return true;
        } catch(Exception exception) {
            throw new ServiceException(exception.toString(), ErrorCode.INTER_SERVER_ERROR);
        }
    }

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
