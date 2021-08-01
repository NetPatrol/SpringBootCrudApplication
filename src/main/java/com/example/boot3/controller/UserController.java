package com.example.boot3.controller;
import com.example.boot3.entity.UserEntity;
import com.example.boot3.model.User;
import com.example.boot3.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@EnableGlobalMethodSecurity(securedEnabled = true)
public class UserController {

    UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    private String login;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        this.login = principal.getName();
        model.addAttribute("login", login);
        model.addAttribute("account", userService.findByLogin(login));
        return "user";
    }
}