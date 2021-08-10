package com.example.boot3.repository.role;

import com.example.boot3.model.Role;

public interface RoleRepository {
    void save(Role role);
    Role findById(Long id);
}
