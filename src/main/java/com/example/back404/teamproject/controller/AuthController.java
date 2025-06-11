package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.common.constants.ApiMappingPattern;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.auth.PasswordResetRequestDto;
import com.example.back404.teamproject.dto.auth.SendMailRequestDto;
import com.example.back404.teamproject.dto.user.request.UserSignInRequestDto;
import com.example.back404.teamproject.dto.user.request.UserSignUpRequestDto;
import com.example.back404.teamproject.dto.user.response.UserSignInResponseDto;
import com.example.back404.teamproject.dto.user.response.UserSignUpResponseDto;
import com.example.back404.teamproject.service.AuthService;
import com.example.back404.teamproject.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiMappingPattern.AUTH_API)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MailService mailService;

    // === AuthController mapping pattern === //
    private static final String POST_SIGN_UP = "/signup";
    private static final String POST_SIGN_IN = "/login";

    @PostMapping(POST_SIGN_UP)
    public ResponseEntity<ResponseDto<UserSignUpResponseDto>> signup(@Valid @RequestBody UserSignUpRequestDto dto) {
        System.out.println("=== 회원가입 요청 도착 ===");
        ResponseDto<UserSignUpResponseDto> response = authService.signup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(POST_SIGN_IN)
    public ResponseEntity<ResponseDto<UserSignInResponseDto>> login(@Valid @RequestBody UserSignInRequestDto dto) {
        ResponseDto<UserSignInResponseDto> response = authService.login(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 3) 이메일 전송
    @PostMapping("/send-email")
    public Mono<ResponseEntity<String>> sendEmail(@Valid @RequestBody SendMailRequestDto dto) {
        return mailService.sendSimpleMessage(dto.getEmail());
    }

    // 4) 이메일 인증
    @GetMapping("/verify")
    public Mono<ResponseEntity<String>> verifyEmail(@RequestParam String token) {
        return mailService.verifyEmail(token);
    }

    // 비밀번호 재설정
    @PostMapping("/reset-password")
    public Mono<ResponseEntity<String>> resetPassword(@Valid @RequestBody PasswordResetRequestDto dto) {
        return authService.resetPassword(dto);
    }
}
