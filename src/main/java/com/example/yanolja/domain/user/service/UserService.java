package com.example.yanolja.domain.user.service;


import com.example.yanolja.domain.user.dto.CreateUserRequest;
import com.example.yanolja.global.util.ResponseDTO;

public interface UserService {

    ResponseDTO<?> signup(CreateUserRequest createUserRequest);

    ResponseDTO<?> deleteUser(Long userId);

}
