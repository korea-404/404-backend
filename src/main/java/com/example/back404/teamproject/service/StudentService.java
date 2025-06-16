package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.constants.ResponseDto;
import com.example.back404.teamproject.dto.students.response.StudentDetailDto;
import com.example.back404.teamproject.dto.students.response.StudentListDto;
import java.util.List;

public interface StudentService {
    ResponseDto<List<StudentListDto>> getAllStudents(String name);
    ResponseDto<StudentDetailDto> getStudentById(String studentId);
}