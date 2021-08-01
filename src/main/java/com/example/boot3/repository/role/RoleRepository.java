package com.example.boot3.repository.role;

import com.example.boot3.entity.RoleEntity;

public interface RoleRepository {
    RoleEntity findById(Long id);
}
