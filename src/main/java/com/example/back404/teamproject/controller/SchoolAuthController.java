package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.common.constants.ApiMappingPattern;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.auth.SchoolSignUpRequestDto;
import com.example.back404.teamproject.dto.auth.UserSignInRequestDto;
import com.example.back404.teamproject.dto.auth.UserSignInResponseDto;
import com.example.back404.teamproject.service.SchoolAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.SCHOOL_AUTH_API) // 예: "/api/v1/auth"
@RequiredArgsConstructor
public class SchoolAuthController {

    private final SchoolAuthService schoolAuthService;

    // 1. 학교 관리자 회원가입
    @PostMapping("/signup")
    public ResponseDto<String> signup(@RequestBody @Valid SchoolSignUpRequestDto requestDto) {
        return schoolAuthService.register(requestDto);
    }

    // 2. 학교 관리자 로그인
    @PostMapping("/login")
    public ResponseDto<UserSignInResponseDto> login(@RequestBody @Valid UserSignInRequestDto requestDto) {
        return schoolAuthService.login(requestDto);
    }

    // 3. 이메일 인증 (토큰 확인)
    @GetMapping("/verify-email")
    public ResponseDto<String> verifyEmail(@RequestParam("token") String token) {
        return schoolAuthService.verifyEmail(token);
    }
}
