package com.example.boot3.repository.role;

import com.example.boot3.entity.RoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository {
    void save(RoleEntity role);
    RoleEntity findById(Long id);
}
