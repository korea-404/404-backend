package com.example.back404.teamproject.service;

import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.auth.PasswordResetRequestDto;
import com.example.back404.teamproject.dto.user.request.UserSignInRequestDto;
import com.example.back404.teamproject.dto.user.request.UserSignUpRequestDto;
import com.example.back404.teamproject.dto.user.response.UserSignInResponseDto;
import com.example.back404.teamproject.dto.user.response.UserSignUpResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AuthService {
    ResponseDto<UserSignUpResponseDto> signup(@Valid UserSignUpRequestDto dto);
    ResponseDto<UserSignInResponseDto> login(@Valid UserSignInRequestDto dto);
    Mono<ResponseEntity<String>> resetPassword(@Valid PasswordResetRequestDto dto);
}