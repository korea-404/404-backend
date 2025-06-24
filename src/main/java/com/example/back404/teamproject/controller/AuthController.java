package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.dto.auth.request.SchoolSignUpRequestDto;
import com.example.back404.teamproject.dto.auth.request.UserSignInRequestDto;
import com.example.back404.teamproject.service.SchoolAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/admin")
@RequiredArgsConstructor
public class AuthController {

    private final SchoolAuthService schoolAuthService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SchoolSignUpRequestDto dto) {
        return ResponseEntity.ok(schoolAuthService.signUp(dto));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody @Valid UserSignInRequestDto dto) {
        return ResponseEntity.ok(schoolAuthService.signIn(dto));
    }

    @PutMapping("/change")
    public ResponseEntity<?> changeAdmin(@RequestParam Long schoolId,
                                         @RequestBody @Valid SchoolSignUpRequestDto newAdminDto) {
        return ResponseEntity.ok(schoolAuthService.changeAdmin(schoolId, newAdminDto));
    }
}