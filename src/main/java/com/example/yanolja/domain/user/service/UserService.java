package com.example.yanolja.domain.user.service;


import com.example.yanolja.domain.user.dto.CreateUserRequest;
import com.example.yanolja.domain.user.dto.CreateUserResponse;

public interface UserService {

    CreateUserResponse signup(CreateUserRequest createUserRequest);

    ResponseDTO<Object> join(CreateUserRequest createUserRequest);

    // 회원탈퇴 메소드, 사용자를 소프트 삭제함
    void deleteUser(Long userId);

}
