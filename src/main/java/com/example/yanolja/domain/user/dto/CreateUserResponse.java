package com.example.yanolja.domain.user.dto;

import com.example.yanolja.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;

public record CreateUserResponse(

    String email,

    String name,

    String phonenumber

) {

    public static CreateUserResponse fromEntity(User user) {
        return new CreateUserResponse(
            user.getEmail(),
            user.getUsername(),
            user.getPhonenumber()
        );
    }
}
