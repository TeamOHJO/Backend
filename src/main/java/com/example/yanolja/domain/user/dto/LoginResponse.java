package com.example.yanolja.domain.user.dto;

import com.example.yanolja.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;

public record LoginResponse(
    @NotNull
    String email,
    @NotNull
    String name,
    @NotNull
    String token

) {

    public static LoginResponse fromEntity(User user,String token) {
        return new LoginResponse(
            user.getEmail(),
            user.getUsername(),
            token
        );
    }
}
