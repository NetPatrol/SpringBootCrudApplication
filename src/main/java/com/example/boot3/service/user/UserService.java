package com.example.boot3.service.user;

import com.example.boot3.entity.UserEntity;

import java.util.List;

public interface UserService {
    void save(UserEntity user);
    List<UserEntity> findAll();
    UserEntity findById(Long id);
    UserEntity findByLogin(String login);
    void edit(UserEntity user);
    void delete(UserEntity user);
}
