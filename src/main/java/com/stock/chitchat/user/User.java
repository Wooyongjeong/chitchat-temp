package com.stock.chitchat.user;

import com.stock.chitchat.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;

    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Builder
    private User(String email, String password, String name, RoleType roleType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.roleType = roleType;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(roleType.name())
        );
    }

    @Override
    public String getUsername() {
        return email;
    }
}
