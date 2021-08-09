package com.example.boot3.controller.rest;

import com.example.boot3.entity.UserEntity;
import com.example.boot3.model.User;
import com.example.boot3.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AdminRestController {

    private final UserServiceImpl userService;
    @Autowired
    public AdminRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST,
                        HttpMethod.HEAD, HttpMethod.OPTIONS,
                        HttpMethod.PUT, HttpMethod.DELETE)
                .build();
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userService.findAll()) {
            if (userEntity != null) {
                users.add(User.toModel(userEntity));
            }
        }
        return ResponseEntity.ok(users);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(User.toModel(userService.findById(id)));
    }

    @Secured({"ROLE_ADMIN"})
    @CrossOrigin
    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserEntity user) {
        userService.save(user);
        return ResponseEntity.ok(User.toModel(userService.findByLogin(user.getLogin())));
    }

    @Secured({"ROLE_ADMIN"})
    @CrossOrigin
    @PutMapping
    public ResponseEntity<User> update(@RequestBody UserEntity user) {
        userService.edit(user);
        return ResponseEntity.ok(User.toModel(userService.findByLogin(user.getLogin())));
    }

    @Secured({"ROLE_ADMIN"})
    @CrossOrigin
    @DeleteMapping
    public ResponseEntity<User> delete(@RequestBody UserEntity user) {
        userService.delete(user);
        return ResponseEntity.ok(User.toModel(userService.findById(user.getId())));
    }
}
