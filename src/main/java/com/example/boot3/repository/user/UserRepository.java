package com.example.boot3.repository.user;

import com.example.boot3.entity.UserEntity;
import java.util.List;

public interface UserRepository {
    void save(UserEntity user);
    List<UserEntity> findAll();
    UserEntity findById(Long id);
    UserEntity findByLogin(String login);
    void delete(UserEntity user);
}
