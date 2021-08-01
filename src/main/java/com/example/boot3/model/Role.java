package com.example.boot3.model;

import com.example.boot3.entity.RoleEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;



@Getter
@Setter
@NoArgsConstructor
public class Role{

    private Long id;
    private String role;

    public static Role toModel(RoleEntity entity) {
        Role model = new Role();
        model.setId(entity.getId());
        model.setRole(entity.getRole());
        return model;
    }
}
