package com.example.boot3.service.role;

import com.example.boot3.entity.RoleEntity;
import com.example.boot3.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void save(RoleEntity entity) {
        roleRepository.save(entity);
    }

    @Override
    public RoleEntity findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Set<RoleEntity> set(RoleEntity role) {
        Set<RoleEntity> setRoles = new HashSet<>();
        setRoles.add(role);
        return setRoles;
    }
}
