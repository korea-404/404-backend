package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.constants.enums.SchoolStatus;
import com.example.back404.teamproject.dto.school.request.SchoolApplicationRequestDto;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.entity.SchoolApplication;
import com.example.back404.teamproject.repository.SchoolApplicationRepository;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.service.SchoolApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class SchoolApplicationServiceImpl implements SchoolApplicationService {

    private final SchoolApplicationRepository repository;
    private final SchoolRepository schoolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseDto<?> register(SchoolApplicationRequestDto dto) {
        try {
            if (dto.getSchoolCode() == null) {
                return ResponseDto.setFailed("학교 코드가 비어있습니다.");
            }

            if (repository.existsBySchoolCode(dto.getSchoolCode())) {
                return ResponseDto.setFailed("이미 존재하는 학교 코드입니다.");
            }

            SchoolApplication application = SchoolApplication.builder()
                    .schoolName(dto.getSchoolName())
                    .schoolAddress(dto.getSchoolAddress())
                    .schoolContactNumber(dto.getSchoolContactNumber())
                    .schoolAdminUsername(dto.getSchoolAdminUsername())
                    .schoolAdminPassword(dto.getSchoolAdminPassword())
                    .schoolAdminName(dto.getSchoolAdminName())
                    .schoolAdminBirthDate(dto.getSchoolAdminBirthDate())
                    .schoolAdminPhoneNumber(dto.getSchoolAdminPhoneNumber())
                    .schoolAdminEmail(dto.getSchoolAdminEmail())
                    .schoolEmail(dto.getSchoolEmail())
                    .applicationStartedDay(dto.getApplicationStartedDay())
                    .applicationLimitedDay(dto.getApplicationLimitedDay())
                    .schoolCode(dto.getSchoolCode())
                    .build();

            SchoolApplication saved = repository.save(application);
            return ResponseDto.setSuccess("학교 등록 신청 완료", saved.getSchoolApplicationId());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("학교 등록 신청 처리 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> approve(Long id) {
        // 생략 – 기존 approve 로직 그대로 유지
        return null;
    }

    @Override
    @Transactional
    public ResponseDto<?> reject(Long id) {
        // 생략 – 기존 reject 로직 그대로 유지
        return null;
    }
}
