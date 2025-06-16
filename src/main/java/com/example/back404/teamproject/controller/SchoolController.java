package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.auth.ChangePasswordRequestDto;
import com.example.back404.teamproject.dto.school.SchoolInfoUpdateRequestDto;
import com.example.back404.teamproject.dto.school.SchoolUpdateRequestDto;
import com.example.back404.teamproject.service.SchoolAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolAuthService schoolAuthService;

    // 7. 관리자 정보 조회
    @GetMapping("/my-info")
    public ResponseDto<?> getMyInfo() {
        return schoolAuthService.getMyInfo();
    }

    // 8. 관리자 정보 수정
    @PutMapping("/my-info")
    public ResponseDto<?> updateMyInfo(@RequestBody SchoolUpdateRequestDto dto) {
        return schoolAuthService.updateMyInfo(dto);
    }

    // 9. 비밀번호 변경
    @PutMapping("/change-password")
    public ResponseDto<?> changePassword(@RequestBody ChangePasswordRequestDto dto) {
        return schoolAuthService.changePassword(dto.getCurrentPassword(), dto.getNewPassword());
    }

    @GetMapping("/info")
    public ResponseDto<?> getSchoolInfo() {
        return schoolAuthService.getSchoolInfo();
    }

    @PutMapping("/info")
    public ResponseDto<?> updateSchoolInfo(@RequestBody SchoolInfoUpdateRequestDto dto) {
        return schoolAuthService.updateSchoolInfo(dto);
    }

}
