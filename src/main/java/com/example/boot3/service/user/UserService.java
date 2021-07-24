package com.example.boot3.service.user;

import com.example.boot3.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> findAll();
    User findById(Long id);
    User findByLogin(String login);
    void edit(User user, String role);
    void delete(User user);
}
