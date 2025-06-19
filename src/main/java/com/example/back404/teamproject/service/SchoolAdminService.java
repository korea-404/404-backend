package com.example.back404.teamproject.service;

import com.example.back404.teamproject.dto.ResponseDto;

public interface SchoolAdminService {
    ResponseDto<?> updateStatus(Long schoolId, String statusStr);
}

