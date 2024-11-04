package com.Bloomify.controller;


import com.Bloomify.dto.RoleDto;
import com.Bloomify.model.Role;
import com.Bloomify.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/role")
public class RoleController extends BaseController<Role, RoleDto, Long> {

    public RoleController(RoleService service) {
        super(service);
    }
}
