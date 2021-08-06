package com.example.boot3.service.user;

import com.example.boot3.entity.RoleEntity;
import com.example.boot3.entity.UserEntity;
import com.example.boot3.repository.role.RoleRepository;
import com.example.boot3.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(UserEntity user) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            user.setRoles(set(roleRepository.findById(2L)));
            user.setPassword(passwordEncoder(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void edit(UserEntity user) {
        UserEntity u = userRepository.findById(user.getId());
        String role = null;
        for (RoleEntity r : user.getRoles()) {
            role = r.getRole();
        }
        u.setName(user.getName());
        u.setLastName(user.getLastName());
        u.setBirthday(user.getBirthday());
        u.setCity(user.getCity());
        u.setWorkplace(user.getWorkplace());
        u.setLogin(user.getLogin());
        if (!user.getPassword().equals("") | !user.getConfirmPassword().equals("")) {
            if (user.getPassword().equals(user.getConfirmPassword())) {
                u.setPassword(passwordEncoder(user.getPassword()));
            }
        }
        if (!role.equals("")) {
            if (role.equals("admin")) {
               u.setRoles(set(roleRepository.findById(1L)));
           } else if (role.equals("user")) {
               u.setRoles(set(roleRepository.findById(2L)));
           }
        }
        userRepository.save(u);
    }

    @Override
    public void delete(UserEntity user) {
        userRepository.delete(user);
    }

    public Set<RoleEntity> set(RoleEntity role) {
        Set<RoleEntity> set = new HashSet<>();
        set.add(role);
        return set;
    }

    public String passwordEncoder(String pass) {
        return passwordEncoder.encode(pass);
    }
}
