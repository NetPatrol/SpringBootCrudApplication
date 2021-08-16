package com.example.boot3.config;

import com.example.boot3.model.Role;
import com.example.boot3.model.User;
import com.example.boot3.service.role.RoleService;
import com.example.boot3.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataBaseInitializer {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DataBaseInitializer(UserService userService,
                               RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    User admin = new User("Дмитрий", "Митрофанов", (byte) 25, "dima@mail.ru", "123","123");
    User user = new User("Сергей", "Пакулов", (byte) 25, "spakulov@mail.ru", "123","123");
    User user1 = new User("Алексей", "Кирпичев", (byte) 25, "kirpicev@mail.ru", "123", "123");

    @PostConstruct
    public void doInit() {
        if (roleService.findAll().isEmpty()) {
            roleService.save(new Role(1L, "ROLE_ADMIN"));
            roleService.save(new Role(2L, "ROLE_USER"));
        }

        if (userService.findAll().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findById(1L));

            admin.setRoles(roles);
            userService.save(admin);
            userService.save(user);
            userService.save(user1);
        }
    }
}
