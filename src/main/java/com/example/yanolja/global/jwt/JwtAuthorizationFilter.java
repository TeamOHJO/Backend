package com.example.yanolja.global.jwt;

import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT를 이용한 인증
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public JwtAuthorizationFilter(
        UserRepository userRepository,
        JwtProvider jwtProvider
    ) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    /**
     * @author liyusang1
     * @implNote
     * header가 아닌 cookie에서 토큰을 가져오려고 하는 경우 아래와 같이 바꾸면 된다.
     *             accessToken = Arrays.stream(request.getCookies())
     *                 .filter(cookie -> cookie.getName().equals(JwtProperties.COOKIE_NAME)).findFirst()
     *                 .map(Cookie::getValue)
     *                 .orElse(null);
     */
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        //header에서 가져옴
        List<String> headerValues = Collections.list(request.getHeaders("Authorization"));
        String accessToken = headerValues.stream()
            .findFirst()
            .map(header -> header.replace("Bearer ", ""))
            .orElse(null);

        //현재 토큰을 사용 하여 인증을 시도 합니다.
        Authentication authentication = getUsernamePasswordAuthenticationToken(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    /**
     * JWT 토큰으로 User를 찾아서 UsernamePasswordAuthenticationToken를 만들어서 반환한다. User가 없다면 null
     */
    private Authentication getUsernamePasswordAuthenticationToken(String token) {
        String email = jwtProvider.getEmail(token);
        if (email != null) {
            return userRepository.findByEmail(email)
                .map(PrincipalDetails::new)
                .map(principalDetails -> new UsernamePasswordAuthenticationToken(
                    principalDetails, // principal
                    null, // credentials
                    principalDetails.getAuthorities()
                )).orElseThrow(IllegalAccessError::new);
        }
        return null; // 유저가 없으면 NULL
    }
}