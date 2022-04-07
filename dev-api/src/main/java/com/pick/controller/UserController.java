package com.pick.controller;

import com.pick.dto.base.ResponseData;
import com.pick.entity.base.ListResponse;
import com.pick.service.UserService;
import com.pick.entity.User;
import com.pick.service.base.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;

    @GetMapping("/all")
    public ListResponse<User> searchAll() {
        return responseService.getListResponse(userService.searchAll());
    }
}
