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

@Controller
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AdminController {

    UserServiceImpl userService;
    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String adminPage(Principal principal,
                            ModelMap modelMap,
                            Model model) {
        model.addAttribute("user", new User());
        modelMap.addAttribute("users", userService.findAll());
        model.addAttribute("account", userService.findByLogin(principal.getName()));
        return "admin";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("account", userService.findByLogin(principal.getName()));
        return "registration";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping ("/registration")
    public String registration(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Principal principal, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("account", userService.findByLogin(principal.getName()));
        return "edit";
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/edit/{id}")
    public String edit(@ModelAttribute User user,
                       @RequestParam String role) {
        userService.edit(user, role);
        return "redirect:/admin";
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("del")
    public String delete(@ModelAttribute User user) {
        userService.delete(user);
        return "redirect:/admin";
    }
}
