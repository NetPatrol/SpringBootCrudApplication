package com.example.boot3.repository.role;

import com.example.boot3.model.Role;

public interface RoleRepository {
    Role findById(Long id);
}
