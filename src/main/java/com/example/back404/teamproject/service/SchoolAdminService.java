package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.school.request.ChangePasswordRequestDto;
import com.example.back404.teamproject.dto.school.request.SchoolInfoUpdateRequestDto;
import com.example.back404.teamproject.dto.school.request.SchoolUpdateRequestDto;

public interface SchoolAdminService {
    ResponseDto<?> getAdminInfo();
    ResponseDto<?> updateAdminInfo(SchoolInfoUpdateRequestDto dto);
    ResponseDto<?> changePassword(ChangePasswordRequestDto dto);
    ResponseDto<?> updateSchoolInfo(Long id, SchoolUpdateRequestDto dto);
}

