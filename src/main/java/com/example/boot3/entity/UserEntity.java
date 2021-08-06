package com.example.boot3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date birthday;
    @Column(name = "city")
    private String city;
    @Column(name = "workplace")
    private String workplace;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "avatar_link")
    private String linkAvatar;
    @Column(name = "password")
    private String password;
    @Column(name = "confirm")
    @Transient
    private String confirmPassword;
    @Column(name = "expired")
    private boolean expired = true;
    @Column(name = "locked")
    private boolean locked = true;
    @Column(name = "credentials")
    private boolean credentials = true;
    @Column(name = "enabled")
    private boolean enabled = true;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<ArticleEntity> articles;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RoleEntity> roles;

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
        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentials;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
