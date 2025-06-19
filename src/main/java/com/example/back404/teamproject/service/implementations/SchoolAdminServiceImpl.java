package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.constants.enums.SchoolStatus;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.service.SchoolAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolAdminServiceImpl implements SchoolAdminService {

    private final SchoolRepository schoolRepository;

    @Override
    public ResponseDto<?> updateStatus(Long schoolId, String statusStr) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new IllegalArgumentException("학교를 찾을 수 없습니다."));

        try {
            SchoolStatus newStatus = SchoolStatus.valueOf(statusStr.toUpperCase());
            school.updateStatus(newStatus);
            schoolRepository.save(school);
            return ResponseDto.setSuccess("학교 상태가 변경되었습니다.", null);
        } catch (IllegalArgumentException e) {
            return ResponseDto.setFailed("유효하지 않은 상태입니다. (PENDING, APPROVED, REJECTED)");
        }
    }
}

