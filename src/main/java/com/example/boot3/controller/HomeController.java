package com.example.boot3.controller;

import com.example.boot3.entity.UserEntity;
import com.example.boot3.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("Welcome to Application");
        messages.add("to continue working, you need to register");
        model.addAttribute("messages", messages);
        model.addAttribute("user", new UserEntity());
        return "index";
    }

}
