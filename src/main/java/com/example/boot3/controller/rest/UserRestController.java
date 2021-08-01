package com.example.boot3.controller.rest;

import com.example.boot3.entity.UserEntity;
import com.example.boot3.model.User;
import com.example.boot3.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class UserRestController {

    UserServiceImpl userService;
    @Autowired
    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{login}")
    public ResponseEntity<User> getOneUserByLogin(@PathVariable("login") String login) {
        return ResponseEntity.ok(User.toModel(userService.findByLogin(login)));
    }
}