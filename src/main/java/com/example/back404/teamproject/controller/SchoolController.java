package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.common.constants.enums.SchoolStatus;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.auth.ChangePasswordRequestDto;
import com.example.back404.teamproject.dto.school.SchoolInfoUpdateRequestDto;
import com.example.back404.teamproject.dto.school.SchoolUpdateRequestDto;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.service.SchoolAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolAuthService schoolAuthService;
    private final SchoolRepository schoolRepository;

    // 7. 관리자 정보 조회
    @GetMapping("/my-info")
    public ResponseDto<?> getMyInfo() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        School school = schoolRepository.findBySchoolAdminEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 관리자입니다."));

        // 상태가 REJECTED일 경우 차단
        if (school.getStatus() == SchoolStatus.REJECTED) {
            return ResponseDto.setFailed("승인되지 않은 학교는 정보를 조회할 수 없습니다.");
        }

        return ResponseDto.setSuccess("내 정보 조회 성공", school);
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