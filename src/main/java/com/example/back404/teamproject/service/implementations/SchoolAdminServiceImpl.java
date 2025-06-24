package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.school.request.ChangePasswordRequestDto;
import com.example.back404.teamproject.dto.school.request.SchoolInfoUpdateRequestDto;
import com.example.back404.teamproject.dto.school.request.SchoolUpdateRequestDto;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.service.SchoolAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchoolAdminServiceImpl implements SchoolAdminService {

    private final SchoolRepository schoolRepository;
    private final PasswordEncoder passwordEncoder;

    private School getCurrentSchool() {
        // 실제 구현에서는 인증된 사용자 정보에서 schoolId 추출
        return schoolRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("학교를 찾을 수 없습니다."));
    }

    @Transactional
    @Override
    public ResponseDto<?> updateAdminInfo(SchoolInfoUpdateRequestDto dto) {
        School school = getCurrentSchool();

        school.setSchoolAdminPhoneNumber(dto.getPhoneNumber());
        school.setSchoolAdminEmail(dto.getEmail());
        school.setSchoolAddress(dto.getSchoolAddress());
        school.setSchoolContactNumber(dto.getSchoolContactNumber());
        school.setSchoolEmail(dto.getSchoolEmail());

        schoolRepository.save(school);
        return ResponseDto.setSuccess("관리자 정보 수정 완료", null);
    }

    @Transactional
    @Override
    public ResponseDto<?> changePassword(ChangePasswordRequestDto dto) {
        School school = getCurrentSchool();

        if (!passwordEncoder.matches(dto.getCurrentPassword(), school.getSchoolPassword())) {
            return ResponseDto.setFailed("현재 비밀번호가 일치하지 않습니다.");
        }

        school.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        schoolRepository.save(school);

        return ResponseDto.setSuccess("비밀번호 변경 완료", null);
    }

    @Transactional
    @Override
    public ResponseDto<?> updateSchoolInfo(Long id, SchoolUpdateRequestDto dto) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("학교를 찾을 수 없습니다."));

        school.setSchoolAddress(dto.getSchoolAddress());
        school.setSchoolContactNumber(dto.getSchoolContactNumber());

        schoolRepository.save(school);
        return ResponseDto.setSuccess("학교 정보 수정 완료", null);
    }

    @Override
    public ResponseDto<?> getAdminInfo() {
        // TODO: 관리자 정보 조회 구현 예정
        return null;
    }
}
