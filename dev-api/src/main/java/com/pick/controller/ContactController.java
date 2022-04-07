package com.pick.controller;

import com.pick.entity.Contact;
import com.pick.entity.User;
import com.pick.entity.base.ListResponse;
import com.pick.service.ContactService;
import com.pick.service.UserService;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;
    private final ResponseService responseService;

    @GetMapping("/all")
    public ListResponse<Contact> searchAll() {
        return responseService.getListResponse(contactService.searchAll());
    }
}
