package com.example.boot3.model;

import com.example.boot3.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class User{
    private Long id;
    private String name;
    private String lastName;
    private Date birthday;
    private String city;
    private String workplace;
    private String linkAvatar;
    private String login;
    private boolean locked;
    private List<Article> articles;
    private List<Role> roles;

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setLastName(entity.getLastName());
        model.setBirthday(entity.getBirthday());
        model.setCity(entity.getCity());
        model.setWorkplace(entity.getWorkplace());
        model.setLinkAvatar(entity.getLinkAvatar());
        model.setLogin(entity.getLogin());
        model.setRoles(entity.getRoles().stream().map(Role::toModel).collect(Collectors.toList()));
        model.setArticles(entity.getArticles().stream().map(Article::toModel).collect(Collectors.toList()));
        return model;
    }
}
