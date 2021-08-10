package com.example.boot3.service.user;

import com.example.boot3.entity.ArticleEntity;
import com.example.boot3.entity.RoleEntity;
import com.example.boot3.entity.UserEntity;
import com.example.boot3.repository.article.ArticleRepository;
import com.example.boot3.repository.user.UserRepository;
import com.example.boot3.service.role.RoleService;
import com.example.boot3.service.role.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleServiceImpl roleService,
                           UserRepository userRepository,
                           ArticleRepository articleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(UserEntity user) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            if (user.getRoles().isEmpty()) {
                user.setRoles(roleService.set(roleService.findById(2L)));
            }
            if (user.getLinkAvatar().equals("")) {
                user.setLinkAvatar("https://sun6-22.userapi.com/impf/c850424/v850424457/7c137/owtRvvT6hOI.jpg?size=200x200&quality=96&proxy=1&sign=5eb1469d2e394794b6f85b94b8d07f87&c_uniq_tag=FyMAiKdQDBmWMrLrbOkAff_ObgO3pB8peGw5aquR18w");
            }
            user.setPassword(passwordEncoder(user.getPassword()));
            userRepository.save(user);
            articleRepository
                    .save(new ArticleEntity("Привет, мир!",
                            "Это ваша первая публикация",
                            LocalDate.now().format(DateTimeFormatter.ISO_DATE),
                            userRepository.findByLogin(user.getLogin())));
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
        if (user.getLinkAvatar() != null) {
            u.setLinkAvatar(user.getLinkAvatar());
        } else if (u.getLinkAvatar() == null) {
            u.setLinkAvatar("https://sun6-22.userapi.com/impf/c850424/v850424457/7c137/owtRvvT6hOI.jpg?size=200x200&quality=96&proxy=1&sign=5eb1469d2e394794b6f85b94b8d07f87&c_uniq_tag=FyMAiKdQDBmWMrLrbOkAff_ObgO3pB8peGw5aquR18w");
        }
        if (!user.getPassword().equals("") | !user.getConfirmPassword().equals("")) {
            if (user.getPassword().equals(user.getConfirmPassword())) {
                u.setPassword(passwordEncoder(user.getPassword()));
            }
        }
        u.setLocked(user.isAccountNonLocked());
        if (role != null && !role.equals("")) {
            if (role.equals("admin")) {
               u.setRoles(roleService.set(roleService.findById(1L)));
           } else if (role.equals("user")) {
               u.setRoles(roleService.set(roleService.findById(2L)));
           }
        }
        userRepository.save(u);
    }

    @Override
    public void delete(UserEntity user) {
        userRepository.delete(user);
    }

    public String passwordEncoder(String pass) {
        return passwordEncoder.encode(pass);
    }
}
