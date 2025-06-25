package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.enums.ErrorCode;
import com.example.back404.teamproject.exception.CustomException;
import com.example.back404.teamproject.dto.auth.request.LoginRequestDto;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentAuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginRequestDto request) {

        // 1. 사용자 존재 여부 확인
        Student student = studentRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // 3. 로그인 성공 처리 (현재는 문자열로 반환)
        return "로그인 성공: " + student.getName();
    }
}
