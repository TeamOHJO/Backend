package com.example.yanolja.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import com.example.yanolja.domain.user.dto.EmailRequest;
import com.example.yanolja.domain.user.service.EmailService;
import com.example.yanolja.global.util.ResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/email/confirmation")
    public ResponseEntity<ResponseDTO<String>> mailConfirm(@RequestBody EmailRequest emailRequest)
        throws Exception {
        emailService.sendVerificationEmail(emailRequest.getEmail());
        return ResponseEntity.ok(
            ResponseDTO.res(HttpStatus.OK, "인증 이메일이 성공적으로 전송되었습니다.", null)
        );
    }

    @GetMapping("/verify/{code}")
    public ResponseEntity<ResponseDTO<Boolean>> verifyEmail(
        @RequestParam("email") String email,
        @PathVariable("code") String code) {

        boolean isVerified = emailService.verifyEmailCode(email, code);
        if (isVerified) {
            return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, "이메일 인증 성공.", true));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDTO.res(HttpStatus.BAD_REQUEST, "이메일 인증 실패.", false));
        }
    }
}