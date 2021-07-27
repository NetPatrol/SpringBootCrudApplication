package com.example.boot3.repository.user;

import com.example.boot3.model.User;

import java.util.List;

public interface UserRepository{
    void save(User user);
    List<User> findAll();
    User findById(Long id);
    User findByLogin(String login);
    void delete(User user);
}
