package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.school.request.SchoolUpdateRequestDto;

public interface SchoolService {
    ResponseDto<?> getSchoolInfo(Long id);
    ResponseDto<?> updateSchoolInfo(Long id, SchoolUpdateRequestDto dto);
}

