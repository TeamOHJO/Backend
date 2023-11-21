package com.example.yanolja.global.springsecurity;

import com.example.yanolja.global.util.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(
        CustomAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException {

        try {

            String authenticationErrorMessage;

            if (exception instanceof BadCredentialsException) {
                authenticationErrorMessage = "이메일 또는 비밀번호 에러";
            } else if (exception instanceof UsernameNotFoundException) {
                authenticationErrorMessage = "존재 하지 않는 유저";
            } else {
                authenticationErrorMessage = "인증 실패";
            }

            // 로그인 실패 JSON 응답을 생성
            ResponseDTO<Object> errorResponse = ResponseDTO.res(
                HttpStatus.BAD_REQUEST,
                authenticationErrorMessage);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(errorResponse);

            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);

        } catch (Exception e) {
            // 예외가 발생하면 로깅하고 적절한 응답을 설정
            logger.error("Error handling authentication failure", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter().write("Authentication failed: " + e.getMessage());
        }
    }
}
