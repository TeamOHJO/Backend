package com.example.yanolja.domain.user.dto;

import com.example.yanolja.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(

    @NotNull
    String email,
    @NotNull
    String password
) {

    public User toEntity() {
        return User.builder()
            .email(email)
            .password(password)
            .build();
    }
}
