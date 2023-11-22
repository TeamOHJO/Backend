package com.example.yanolja.domain.user.service;


import com.example.yanolja.domain.user.dto.CreateUserRequest;
import com.example.yanolja.domain.user.dto.CreateUserResponse;
import com.example.yanolja.global.util.ResponseDTO;

public interface UserService {

    CreateUserResponse signup(CreateUserRequest createUserRequest);

    ResponseDTO<?> join(CreateUserRequest createUserRequest);

    ResponseDTO<?> deleteUser(Long userId);

}
