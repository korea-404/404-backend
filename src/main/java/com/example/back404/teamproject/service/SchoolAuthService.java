package com.example.back404.teamproject.service;

import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.auth.SchoolSignUpRequestDto;
import com.example.back404.teamproject.dto.auth.UserSignInRequestDto;
import com.example.back404.teamproject.dto.auth.UserSignInResponseDto;
import com.example.back404.teamproject.dto.school.SchoolInfoUpdateRequestDto;
import com.example.back404.teamproject.dto.school.SchoolUpdateRequestDto;

public interface SchoolAuthService {

    // 회원가입
    ResponseDto<String> register(SchoolSignUpRequestDto requestDto);

    // 로그인
    ResponseDto<UserSignInResponseDto> login(UserSignInRequestDto requestDto);

    // 이메일 인증
    ResponseDto<String> verifyEmail(String token);

    // 관리자 정보 수정
    ResponseDto<?> updateMyInfo(SchoolUpdateRequestDto dto);

    // 비밀번호 변경
    ResponseDto<?> changePassword(String currentPassword, String newPassword);

    // 관리자 정보 조회
    ResponseDto<?> getMyInfo();

    ResponseDto<?> getSchoolInfo();

    ResponseDto<?> updateSchoolInfo(SchoolInfoUpdateRequestDto dto);

}
