package com.example.yanolja.domain.user.dto;

import com.example.yanolja.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public record CreateUserRequest(
    @NotNull
    @Email(message = "이메일 형식이 유효하지 않습니다.")
    String email,

    @NotNull
    String username,

    @NotNull
    String password,

    @NotNull
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "전화번호 형식이 유효하지 않습니다.")
    String phonenumber

) {



    public User toEntity() {
        return User.builder()
            .email(email)
            .username(username)
            .password(passwordEncoder.encode(password))
            .phonenumber(phonenumber)
            .authority("ROLE_USER")
            .updatedAt(LocalDateTime.now())
            .build();
    }


}
