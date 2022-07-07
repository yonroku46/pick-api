package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.ContactReqDto;
import com.pick.dto.response.BooleanResDto;
import com.pick.repository.ContactRepository;
import com.pick.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
