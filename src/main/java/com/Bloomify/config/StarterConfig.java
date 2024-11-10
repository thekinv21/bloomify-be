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

            RoleDto ADMIN_ROLE_DTO = new RoleDto();
            ADMIN_ROLE_DTO.setName("ADMIN");
            ADMIN_ROLE_DTO.setIsActive(true);

            RoleDto USER_ROLE_DTO = new RoleDto();
            USER_ROLE_DTO.setName("USER");
            USER_ROLE_DTO.setIsActive(true);

            RoleDto SUPER_ADMIN_ROLE_DTO = new RoleDto();
            SUPER_ADMIN_ROLE_DTO.setName("SUPER_ADMIN");
            SUPER_ADMIN_ROLE_DTO.setIsActive(true);

            RoleDto MOD_ROLE_DTO = new RoleDto();
            MOD_ROLE_DTO.setName("MOD");
            MOD_ROLE_DTO.setIsActive(true);

            RoleDto ADMIN_ROLE = roleService.create(ADMIN_ROLE_DTO);
            RoleDto USER_ROLE = roleService.create(USER_ROLE_DTO);
            RoleDto SUPER_ADMIN_ROLE = roleService.create(SUPER_ADMIN_ROLE_DTO);
            RoleDto MOD_ROLE = roleService.create(MOD_ROLE_DTO);

            UserDto ADMIN = new UserDto();
            ADMIN.setFirstName("John");
            ADMIN.setLastName("Doe");
            ADMIN.setEmail("admin@gmail.com");
            ADMIN.setUsername("admin");
            ADMIN.setPassword("pass");
            ADMIN.setActive(true);
            ADMIN.setRoles(Set.of(ADMIN_ROLE.getName(), SUPER_ADMIN_ROLE.getName()));
            userService.create(ADMIN);


            UserDto USER = new UserDto();
            USER.setFirstName("Erling");
            USER.setLastName("Holland");
            USER.setEmail("holland@gmail.com");
            USER.setUsername("erling");
            USER.setPassword("pass");
            USER.setActive(true);
            USER.setRoles(Set.of(USER_ROLE.getName()));
            userService.create(USER);


            UserDto SUPER_ADMIN = new UserDto();
            SUPER_ADMIN.setFirstName("Emilia");
            SUPER_ADMIN.setLastName("Johnson");
            SUPER_ADMIN.setEmail("emilia@gmail.com");
            SUPER_ADMIN.setUsername("emilia");
            SUPER_ADMIN.setPassword("pass");
            SUPER_ADMIN.setActive(true);
            SUPER_ADMIN.setRoles(Set.of(ADMIN_ROLE.getName()));
            userService.create(SUPER_ADMIN);


            UserDto MOD = new UserDto();
            MOD.setFirstName("Nil");
            MOD.setLastName("Watson");
            MOD.setEmail("nil@gmail.com");
            MOD.setUsername("watson");
            MOD.setPassword("pass");
            MOD.setActive(true);
            MOD.setRoles(Set.of(MOD_ROLE.getName()));
            userService.create(MOD);


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
