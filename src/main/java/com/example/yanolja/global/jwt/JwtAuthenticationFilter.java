package com.example.yanolja.global.jwt;

import com.example.yanolja.domain.user.dto.LoginRequest;
import com.example.yanolja.domain.user.dto.LoginResponse;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.global.springsecurity.CustomAuthenticationFailureHandler;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * JWT를 이용한 로그인 인증
 */
@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtProvider jwtProvider;
    UserRepository userRepository;
    CustomAuthenticationFailureHandler authenticationFailureHandler;

    public JwtAuthenticationFilter(
        AuthenticationManager authenticationManager,
        JwtProvider jwtProvider,
        UserRepository userRepository,
        CustomAuthenticationFailureHandler authenticationFailureHandler
    ) {
        this.authenticationFailureHandler = authenticationFailureHandler;
        setAuthenticationFailureHandler(authenticationFailureHandler);
        super.setAuthenticationManager(authenticationManager);
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
    }

    /**
     * 로그인 인증 시도
     */
    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {
        try {
            // 요청된 JSON 데이터를 객체로 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(),
                LoginRequest.class);

            // 로그인할 때 입력한 email과 password를 가지고 authenticationToken를 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password(),
                new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_USER")))
            );

            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 인증성공
     */
    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult
    ) throws IOException {
        User user = ((PrincipalDetails) authResult.getPrincipal()).getUser();
        String token = jwtProvider.createToken(user);
        // 쿠키 생성
        Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, token);
        cookie.setMaxAge(JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME / 1000 * 2); // setMaxAge는 초단위
        cookie.setPath("/");
        response.addCookie(cookie);
        //response.sendRedirect("/");  // 발급후 redirect로 이동 -> 클라이언트에게 http 리다이렉션 요청 코드

        // 로그인 성공 시 JSON 응답을 생성
        ResponseDTO<Object> errorResponse = ResponseDTO.res(
            HttpStatus.valueOf(HttpServletResponse.SC_OK),
            "Login successful",
            new LoginResponse(user.getEmail(), user.getUsername()));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    /**
     * 인증실패
     */
    @Override
    protected void unsuccessfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException failed
    ) throws IOException, ServletException {
        authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
    }
}