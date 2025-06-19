package com.example.back404.teamproject.config;

import com.example.back404.teamproject.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // CORS 기본 허용
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 이메일 인증, 로그인/회원가입은 모두 허용
                        .requestMatchers("/api/v1/mail/**").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // 공지사항 GET 조회만 허용 (POST, PUT은 권한 필요)
                        .requestMatchers(HttpMethod.GET, "/api/v1/notice/**").permitAll()

                        // 학교 신청 등록, 신청 조회 등은 관리자만 가능
                        .requestMatchers("/api/v1/school-application/**").hasRole("ADMIN")

                        // 학교 정보 CRUD도 관리자만 가능
                        .requestMatchers("/api/v1/school/**").hasRole("ADMIN")

                        // 그 외 요청은 인증된 사용자만
                        .anyRequest().authenticated()
                );

        // JWT 필터 등록
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
