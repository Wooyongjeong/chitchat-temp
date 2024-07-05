package com.stock.chitchat.user.dto;

import com.stock.chitchat.user.RoleType;
import com.stock.chitchat.user.User;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
public class JoinDTO {
    private String email;
    private String password;
    private String name;
    private RoleType roleType;

    public User toEntity(String encryptedPassword) {
        return User.builder()
                .email(email)
                .password(encryptedPassword)
                .name(name)
                .roleType(roleType)
                .build();
    }
}
