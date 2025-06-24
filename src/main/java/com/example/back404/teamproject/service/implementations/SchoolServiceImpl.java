package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.school.request.SchoolUpdateRequestDto;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.service.SchoolService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Override
    public ResponseDto<?> getSchoolInfo(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("학교를 찾을 수 없습니다."));
        return ResponseDto.setSuccess("학교 정보 조회 완료", school);
    }

    @Override
    @Transactional
    public ResponseDto<?> updateSchoolInfo(Long id, SchoolUpdateRequestDto dto) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("학교를 찾을 수 없습니다."));
        school.setSchoolAddress(dto.getSchoolAddress());
        school.setSchoolContactNumber(dto.getSchoolContactNumber());
        schoolRepository.save(school);
        return ResponseDto.setSuccess("학교 정보 수정 완료", null);
    }
}