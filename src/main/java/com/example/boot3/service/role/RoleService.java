package com.example.boot3.service.role;

import com.example.boot3.model.Role;
import java.util.Set;

public interface RoleService {
    void save(Role role);
    Role findById(Long id);
    Set<Role> set(Role role);
}
