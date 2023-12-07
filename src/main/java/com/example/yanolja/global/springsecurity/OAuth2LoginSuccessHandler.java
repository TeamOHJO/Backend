package com.example.yanolja.global.springsecurity;

import com.example.yanolja.domain.user.dto.LoginResponse;
import com.example.yanolja.global.jwt.JwtProvider;
import com.example.yanolja.global.util.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        // OAuth2 로그인이 성공했을 때의 추가 작업을 수행
        // 여기에서는 JWT 토큰을 발급하고 형식에 맞게 return

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String token = jwtProvider.createToken(principalDetails.getUser());

/*        // 응답 헤더에 JWT 토큰 추가
        response.addHeader("Authorization", "Bearer " + token);

        // JWT 토큰을 response에 담아서 전송
        response.setContentType("application/json");
        response.getWriter().write("{\"token\": \"" + token + "\"}");
        System.out.println("TOKEN : "+token);*/

        ResponseDTO<Object> loginResponse = ResponseDTO.res(
            HttpStatus.valueOf(HttpServletResponse.SC_OK),
            "Login successful",
            new LoginResponse(principalDetails.getUser().getEmail(),
                principalDetails.getUser().getUsername(), token));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(loginResponse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
