package com.example.yanolja.global.springsecurity;

import com.example.yanolja.global.jwt.JwtAuthenticationFilter;
import com.example.yanolja.global.jwt.JwtAuthorizationFilter;
import com.example.yanolja.global.jwt.JwtProperties;
import com.example.yanolja.global.util.CustomResponseUtil;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * Security 설정 Config
 */
@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
        HandlerMappingIntrospector introspector) throws Exception {

        // CORS 설정 추가
        http.cors(cors -> cors
            .configurationSource(request -> {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.applyPermitDefaultValues();
                configuration.addAllowedOriginPattern(
                    "https://dashing-tiramisu-cbdade.netlify.app");
                configuration.addAllowedOriginPattern("http://localhost:5173");
                configuration.setAllowedMethods(
                    Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "HEAD"));

                // 다른 도메인도 필요에 따라 추가
                configuration.setAllowCredentials(true); // 쿠키를 포함한 크로스 도메인 요청을 허용
                return configuration;
            }));

        // basic authentication
        http.httpBasic(AbstractHttpConfigurer::disable); // basic authentication filter 비활성화
        // csrf
        http.csrf(AbstractHttpConfigurer::disable);
        // remember-me
        http.rememberMe(AbstractHttpConfigurer::disable);
        // stateless
        http.sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // jwt filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class);

        http.authorizeHttpRequests(authz -> authz
            /*
            .requestMatchers(new AntPathRequestMatcher("ant matcher")).authenticated()
            .requestMatchers(new AntPathRequestMatcher("role sample")).hasRole("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("role sample", HttpMethod.POST.name())).hasRole("ADMIN") */
            .requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/user/signup/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/user/email/confirmation/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/user/verify/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/accommodation/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/fetch/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/review/accommodation/**")).permitAll()


            .requestMatchers(HttpMethod.OPTIONS, "/basket/**").permitAll() // OPTIONS 메서드에 대한 권한 허용
            .anyRequest().authenticated());

        http.exceptionHandling(exceptionHandling -> {
            exceptionHandling.authenticationEntryPoint(
                (request, response, authException) -> CustomResponseUtil.fail(response,
                    "로그인을 진행해 주세요", HttpStatus.UNAUTHORIZED));

            exceptionHandling.accessDeniedHandler(
                (request, response, accessDeniedException) -> CustomResponseUtil.fail(response,
                    "접근 권한이 없습니다", HttpStatus.FORBIDDEN));
        });

        // logout 설정
        http.logout(logout -> logout
            .deleteCookies(JwtProperties.COOKIE_NAME)
            .logoutSuccessHandler(logoutSuccessHandler));

        return http.build();
    }
}