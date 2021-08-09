package com.example.boot3.service.role;

import com.example.boot3.entity.RoleEntity;

import java.util.Set;

public interface RoleService {
    void save(RoleEntity role);
    RoleEntity findById(Long id);
    Set<RoleEntity> set(RoleEntity role);
}
