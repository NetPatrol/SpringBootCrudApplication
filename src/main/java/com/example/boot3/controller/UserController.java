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

    @GetMapping("/")
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

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/{login}")
    public String adminPage(@PathVariable String login,
                            ModelMap modelMap,
                            Model model) {
        model.addAttribute("user", new User());
        modelMap.addAttribute("users", userService.findAll());
        model.addAttribute("account", userService.findByLogin(login));
        return "admin";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user/{login}")
    public String userPageById(@PathVariable("login") String login,
                               Principal principal,
                               Model model) {
        model.addAttribute("user", userService.findByLogin(login));
        model.addAttribute("account", userService.findByLogin(principal.getName()));
        return "user";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute User user,
                               Principal principal) {
        userService.save(user);
        if (principal != null) {
            return "redirect:/admin/" + principal.getName();
        } else {
            return "redirect:/";
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("edit")
    public String edit(@ModelAttribute("user") User user,
                       @RequestParam String role,
                       Principal principal) {
        userService.edit(user, role);
        return "redirect:/admin/" + principal.getName();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("delete")
    public String delete(@ModelAttribute("user") User user,
                         Principal principal) {
        userService.delete(user);
        return "redirect:/admin/" + principal.getName();
    }
}
