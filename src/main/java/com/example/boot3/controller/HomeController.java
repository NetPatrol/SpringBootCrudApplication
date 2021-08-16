package com.example.boot3.controller;

import com.example.boot3.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
@Controller
@RequestMapping("/")
public class HomeController {
    UserServiceImpl userService;
    UserController userController;
    @Autowired
    public HomeController(UserServiceImpl userService, UserController userController) {
        this.userService = userService;
        this.userController = userController;
    }

    @GetMapping
    public String getHomePage(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("account", userService.findByLogin(principal.getName()));
            return userController.userPage(principal.getName(), principal, model);
        }
        return "index";
    }
}
