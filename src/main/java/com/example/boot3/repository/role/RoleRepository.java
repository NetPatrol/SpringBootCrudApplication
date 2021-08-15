package com.example.boot3.repository.role;

import com.example.boot3.model.Role;

import java.util.List;

public interface RoleRepository {
    void save(Role role);
    List<Role> findAll();
    Role findById(Long id);
}
