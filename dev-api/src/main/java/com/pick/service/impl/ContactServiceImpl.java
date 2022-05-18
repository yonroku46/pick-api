package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.ContactReqDto;
import com.pick.dto.response.BooleanResDto;
import com.pick.entity.Contact;
import com.pick.exception.ErrorCode;
import com.pick.exception.ServiceException;
import com.pick.repository.ContactRepository;
import com.pick.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public ResponseData contact(ContactReqDto req) {
        String name = req.getName();
        String email = req.getEmail();
        String category = req.getCategory();
        String detail = req.getDetail();
        contactRepository.save(name, email, category, detail);
        BooleanResDto response = new BooleanResDto();
        response.setResult(true);
        return response;
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
