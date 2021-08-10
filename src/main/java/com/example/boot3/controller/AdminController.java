package com.example.boot3.controller;

import com.example.boot3.entity.ArticleEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AdminController {

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public String userPage(Principal principal, Model model) {
        model.addAttribute("account", principal.getName());
        model.addAttribute("article", new ArticleEntity());
        return "admin";
    }
}
