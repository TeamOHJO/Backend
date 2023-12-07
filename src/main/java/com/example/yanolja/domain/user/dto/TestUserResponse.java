package com.example.yanolja.domain.user.dto;

import com.example.yanolja.domain.user.entity.User;

public record TestUserResponse(

    String email,
    String name,
    String phonenumber,
    String provider

) {

    public static TestUserResponse fromEntity(User user) {
        return new TestUserResponse(
            user.getEmail(),
            user.getUsername(),
            user.getPhonenumber(),
            user.getProvider()
        );
    }
}
