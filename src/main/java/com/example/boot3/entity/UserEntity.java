package com.example.boot3.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private  Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "city")
    private String city;
    @Column(name = "workplace")
    private String workplace;
    @Column(name = "avatar_link")
    private String linkAvatar;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "password")
    private String password;
    @Transient
    private String confirmPassword;
    @Column(name = "locked")
    private boolean locked = true;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<ArticleEntity> articles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity(String name, String lastName, String birthday,
                      String city, String workplace, String linkAvatar,
                      String login, String password, String confirmPassword) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.city = city;
        this.workplace = workplace;
        this.linkAvatar = linkAvatar;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public UserEntity(String login) {
        this.login = login;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
