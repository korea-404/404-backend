package com.example.back404.teamproject.service;

import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.teachers.response.TeacherListDto;
import java.util.List;

public interface TeacherService {
    ResponseDto<List<TeacherListDto>> getAllTeachers();
}