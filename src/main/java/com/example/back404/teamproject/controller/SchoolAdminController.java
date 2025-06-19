package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.school.SchoolStatusUpdateRequestDto;
import com.example.back404.teamproject.service.SchoolAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/schools")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SchoolAdminController {

    private final SchoolAdminService schoolAdminService;

    @PutMapping("/{schoolId}/status")
    public ResponseDto<?> updateSchoolStatus(
            @PathVariable Long schoolId,
            @RequestBody @Valid SchoolStatusUpdateRequestDto dto
    ) {
        return schoolAdminService.updateStatus(schoolId, dto.getStatus());
    }
}

