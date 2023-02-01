package com.example.websocketchatting.domain.dto;

import com.example.websocketchatting.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequest {

    private String userName;
    private String password;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .userName(userName)
                .password(encodedPassword)
                .role("USER")
                .build();
    }
}
