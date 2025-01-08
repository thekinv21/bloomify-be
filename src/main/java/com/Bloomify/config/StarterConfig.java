package com.Bloomify.config;


import com.Bloomify.dto.RoleDto;
import com.Bloomify.dto.UserDto;
import com.Bloomify.service.RoleService;
import com.Bloomify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor

public class StarterConfig implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        //dummyData();
    }

    private void dummyData() {

        try {

            RoleDto USER_ROLE_DTO = new RoleDto();
            USER_ROLE_DTO.setName("USER");
            USER_ROLE_DTO.setIsActive(true);

            RoleDto ADMIN_ROLE_DTO = new RoleDto();
            ADMIN_ROLE_DTO.setName("ADMIN");
            ADMIN_ROLE_DTO.setIsActive(true);

            RoleDto SUPER_ADMIN_ROLE_DTO = new RoleDto();
            SUPER_ADMIN_ROLE_DTO.setName("SUPER_ADMIN");
            SUPER_ADMIN_ROLE_DTO.setIsActive(true);

            RoleDto MOD_ROLE_DTO = new RoleDto();
            MOD_ROLE_DTO.setName("MOD");
            MOD_ROLE_DTO.setIsActive(true);

            RoleDto USER_ROLE = roleService.create(USER_ROLE_DTO);
            RoleDto ADMIN_ROLE = roleService.create(ADMIN_ROLE_DTO);

            UserDto ADMIN = new UserDto();
            ADMIN.setFirstName("John");
            ADMIN.setLastName("Doe");
            ADMIN.setEmail("vadimkiniabaev@gmail.com");
            ADMIN.setUsername("admin");
            ADMIN.setPassword("pass");
            ADMIN.setActive(true);
            ADMIN.setRoles(Set.of(ADMIN_ROLE.getName()));
            userService.create(ADMIN);


            UserDto USER = new UserDto();
            USER.setFirstName("Erling");
            USER.setLastName("Holland");
            USER.setEmail("thekinv21@gmail.com");
            USER.setUsername("user");
            USER.setPassword("pass");
            USER.setActive(true);
            USER.setRoles(Set.of(USER_ROLE.getName()));
            userService.create(USER);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
