package com.example.boot3.controller;

import com.example.boot3.model.User;
import com.example.boot3.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;
    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("account",
                    userService.findByLogin(principal.getName()));
            return "redirect:user/" + principal.getName();
        } else {
            List<String> messages = new ArrayList<>();
            messages.add("Hello!");
            messages.add("Welcome to Application");
            messages.add("to continue working, you need to register");
            model.addAttribute("messages", messages);
            model.addAttribute("user", new User());
            return "index";
        }
    }

    @PostMapping
    public String registration(@ModelAttribute User user) {
        userService.save(user);
            return "redirect:/login";
    }
}
