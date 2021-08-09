package com.example.boot3.controller;

import com.example.boot3.model.User;
import com.example.boot3.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@EnableGlobalMethodSecurity(securedEnabled = true)
public class UserController {

    UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    private String login;

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("Welcome to Application");
        messages.add("to continue working, you need to register");
        model.addAttribute("messages", messages);
        model.addAttribute("user", new User());
        return "index";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String adminPage(Principal principal, ModelMap modelMap, Model model) {
        this.login = principal.getName();
        model.addAttribute("login", login);
        model.addAttribute("user", new User());
        modelMap.addAttribute("users", userService.findAll());
        model.addAttribute("account", userService.findByLogin(login));
        return "admin";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user/{login}")
    public String userPageById(@PathVariable("login") String log, Principal principal, Model model) {
        this.login = principal.getName();
        model.addAttribute("login", login);
        model.addAttribute("user", userService.findByLogin(log));
        model.addAttribute("account", userService.findByLogin(login));
        return "user";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String registration(Principal principal, @ModelAttribute User user) {
        userService.save(user);
        if (principal != null) {
            return "redirect:/admin";
        } else {
            return "redirect:/";
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("edit")
    public String edit(@ModelAttribute("user") User user,
                       @RequestParam String role) {
        userService.edit(user, role);
        return "redirect:/admin";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("delete")
    public String delete(@ModelAttribute("user") User user) {
        userService.delete(user);
        return "redirect:/admin";
    }
}
