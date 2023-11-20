package com.example.yanolja.domain.user.service;


import com.example.yanolja.domain.user.dto.CreateUserRequest;
import com.example.yanolja.domain.user.dto.CreateUserResponse;

public interface UserService {

    CreateUserResponse signup(CreateUserRequest createUserRequest);
}
