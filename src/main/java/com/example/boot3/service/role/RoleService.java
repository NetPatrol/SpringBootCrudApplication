package com.example.boot3.service.role;

import com.example.boot3.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void save(Role role);
    Role findById(Long id);
    List<Role> findAll();
    Set<Role> set(Role role);
}
