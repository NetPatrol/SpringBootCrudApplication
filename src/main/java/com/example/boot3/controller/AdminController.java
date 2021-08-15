package com.example.boot3.controller;

import com.example.boot3.model.User;
import com.example.boot3.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AdminController {

    UserServiceImpl userService;
    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Secured("ROLE_ADMIN")
    @GetMapping("{login}")
    public String adminPage(@PathVariable String login,
                            ModelMap modelMap,
                            Model model) {
        model.addAttribute("user", new User());
        modelMap.addAttribute("users", userService.findAll());
        model.addAttribute("account", userService.findByLogin(login));
        return "admin";
    }
    
    @Secured("ROLE_ADMIN")
    @PostMapping("{login}")
    public String registration(@ModelAttribute("user") User user, Principal principal) {
        userService.save(user);
        return "redirect:" + principal.getName();
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/upd")
    public String edit(@ModelAttribute("user") User user,
                     @RequestParam String role,
                     Principal principal) {
        userService.edit(user, role);
        return "redirect:/admin/" + principal.getName();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/del")
    public String delete(@ModelAttribute("user") User user,
                         Principal principal) {
        userService.delete(userService.findById(user.getId()));
        return "redirect:/admin/" + principal.getName();
    }
}