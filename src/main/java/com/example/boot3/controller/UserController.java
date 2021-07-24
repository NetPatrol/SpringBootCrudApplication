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
public class UserController {

    UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    private String login;

    @GetMapping("/")
    public String getHomePage(Model model) {
        if (login != null) {
            model.addAttribute("login", login);
            model.addAttribute("account", userService.findByLogin(login));
        }
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
    @Secured("ROLE_USER")
    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        this.login = principal.getName();
        model.addAttribute("login", login);
        model.addAttribute("account", userService.findByLogin(login));
        return "user";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("login", login);
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("account", userService.findByLogin(login));
        return "edit";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute User user, @RequestParam String role) {
        userService.edit(user, role);
        return "redirect:/admin";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute User user) {
        userService.delete(user);
        return "redirect:/admin";
    }
}
