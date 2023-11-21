package com.example.yanolja.domain.user.controller;

import com.example.yanolja.domain.user.dto.CreateUserRequest;
import com.example.yanolja.domain.user.dto.CreateUserResponse;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.service.UserService;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ResponseDTO<?>> join(@Valid @RequestBody CreateUserRequest createUserRequest) {
        ResponseDTO<Object> response = userService.join(createUserRequest);
        return ResponseEntity.status(HttpStatus.valueOf(response.getCode())).body(response);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO<?>> deleteMember(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            Long userId = jwtService.getUserIdFromToken(token);
            userService.deleteUser(userId);

            return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, "회원 탈퇴가 완료되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.res(HttpStatus.INTERNAL_SERVER_ERROR, "회원 탈퇴 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }






    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.signup(createUserRequest));
    }

    // Authenticated user 샘플테스트 코드입니다
    @GetMapping("/test")
    public ResponseEntity<?> test(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();

        CreateUserResponse createUserResponse = new CreateUserResponse(user.getEmail(),
            user.getUsername());
        return ResponseEntity.ok(createUserResponse);
    }
}
