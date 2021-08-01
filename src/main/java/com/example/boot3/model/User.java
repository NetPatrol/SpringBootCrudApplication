package com.example.boot3.model;

import com.example.boot3.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class User{
    private Long id;
    private String name;
    private String lastName;
    private String login;
    private boolean locked;
    private List<Role> roles;

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setLastName(entity.getLastName());
        model.setLogin(entity.getLogin());
        model.setRoles(entity.getRoles().stream().map(Role::toModel).collect(Collectors.toList()));
        return model;
    }
}
