package com.example.boot3.service.user;

import com.example.boot3.model.Role;
import com.example.boot3.model.User;
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
    public void save(User user) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            user.setRoles(set(roleRepository.findById(2L)));
            user.setPassword(passwordEncoder(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void edit(User user, String role) {
        User u = userRepository.findById(user.getId());
            if (!role.equals("")) {
                if (role.equals("admin")) {
                    u.setRoles(set(roleRepository.findById(1L)));
                } else if (role.equals("user")) {
                    u.setRoles(set(roleRepository.findById(2L)));
                }
            }
            if (!user.getPassword().equals("") | !user.getConfirmPassword().equals("")) {
                if (user.getPassword().equals(user.getConfirmPassword())) {
                    u.setPassword(passwordEncoder(user.getPassword()));
                }
            }
        userRepository.edit(u);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    public Set<Role> set(Role role) {
        Set<Role> set = new HashSet<>();
        set.add(role);
        return set;
    }

    public String passwordEncoder(String pass) {
        return passwordEncoder.encode(pass);
    }
}
