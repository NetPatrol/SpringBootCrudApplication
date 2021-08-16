package com.example.boot3.controller;

import com.example.boot3.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
@Controller
@RequestMapping("/user")
public class UserController {

    UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("{login}")
    public String userPage(@PathVariable("login") String login,
                           Principal principal,
                           Model model) {
        model.addAttribute("user", userService.findByLogin(login));
        model.addAttribute("account", userService.findByLogin(principal.getName()));
        return "user";
    }
}
