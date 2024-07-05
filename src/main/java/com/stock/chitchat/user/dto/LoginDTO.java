package com.stock.chitchat.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class LoginDTO {
    private String email;
    private String password;
}
