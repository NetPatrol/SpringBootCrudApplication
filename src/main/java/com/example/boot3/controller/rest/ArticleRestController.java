package com.example.boot3.controller.rest;

import com.example.boot3.entity.ArticleEntity;
import com.example.boot3.model.Article;
import com.example.boot3.service.article.ArticleService;
import com.example.boot3.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ArticleRestController {

    UserService userService;
    ArticleService articleService;
    @Autowired
    public ArticleRestController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @CrossOrigin
    @PostMapping ("")
    public ResponseEntity<Article> save(@RequestBody ArticleEntity article) {
        articleService.save(article, article.getUser());
        return ResponseEntity.ok(Article.toModel(articleService.findByTitle(article.getTitle())));
    }
}
