package com.Bloomify.controller;


import com.Bloomify.dto.UserDto;
import com.Bloomify.model.User;
import com.Bloomify.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/api/user")
public class UserController extends BaseController<User, UserDto, UUID> {

    public UserController(UserService service) {
        super(service);
    }
}
