package com.example.boot3.service.role;

import com.example.boot3.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void save(Role role);
    List<Role> findAll();
    Role findById(Long id);
    Set<Role> set(Role role);
}
