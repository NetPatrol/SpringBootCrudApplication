package com.example.boot3.service.user;

import com.example.boot3.model.User;
import com.example.boot3.repository.user.UserRepository;
import com.example.boot3.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleService roleService,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            if (user.getRoles().isEmpty()) {
                user.setRoles(roleService.set(roleService.findById(2L)));
            }
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
        u.setName(user.getName());
        u.setLastName(user.getLastName());
        u.setAge(user.getAge());
        u.setLogin(user.getLogin());
        if (!role.equals("")) {
            if (role.equals("admin")) {
                u.setRoles(roleService.set(roleService.findById(1L)));
            } else if (role.equals("user")) {
                u.setRoles(roleService.set(roleService.findById(2L)));
            }
        }
        if (!user.getPassword().equals("") | !user.getConfirmPassword().equals("")) {
            if (user.getPassword().equals(user.getConfirmPassword())) {
                u.setPassword(passwordEncoder(user.getPassword()));
            }
        }
        userRepository.save(u);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }


    public String passwordEncoder(String pass) {
        return passwordEncoder.encode(pass);
    }
}
