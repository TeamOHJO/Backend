package com.example.yanolja.domain.user.dto;

import com.example.yanolja.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public record CreateUserRequest(
    @NotNull
    String email,

    @NotNull
    String username,

    @NotNull
    String password,

    @NotNull
    String phonenumber

) {

    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User toEntity() {
        return User.builder()
            .email(email)
            .username(username)
            .password(passwordEncoder.encode(password))
            .phonenumber(phonenumber)
            .authority("ROLE_USER")
            .createdAt(LocalDateTime.now())
            .build();
    }


}
