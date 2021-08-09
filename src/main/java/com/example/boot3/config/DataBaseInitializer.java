package com.example.boot3.config;

import com.example.boot3.entity.RoleEntity;
import com.example.boot3.entity.UserEntity;
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

    UserEntity admin = new UserEntity("Дмитрий","Митрофанов","25.06.1986","Санкт-Петербург",
            "OOO GoldFish",  "https://mdbootstrap.com/img/new/avatars/2.jpg", "dima@mail.ru",
            "123","123");

    UserEntity user = new UserEntity("Сергей","Пакулов","18.03.1983","Санкт-Петербург",
            "OOO GoldFish",  "https://mdbootstrap.com/img/new/avatars/1.jpg", "spakulov@mail.ru",
            "123","123");

    UserEntity user1 = new UserEntity("Алексей","Кирпичев","05.12.1987","Санкт-Петербург",
            "OOO GoldFish",  "https://mdbootstrap.com/img/new/avatars/3.jpg", "kirpicev@mail.ru",
            "123","123");

    @PostConstruct
    public void doInit() {
        roleService.save(new RoleEntity(1L, "ROLE_ADMIN"));
        roleService.save(new RoleEntity(2L, "ROLE_USER"));

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleService.findById(1L));

        if (userService.findAll().isEmpty()) {
            admin.setRoles(roles);
            userService.save(admin);
            userService.save(user);
            userService.save(user1);
        }
    }
}
