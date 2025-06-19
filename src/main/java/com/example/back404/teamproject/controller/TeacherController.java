package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.common.constants.ApiMappingPattern;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.teachers.response.TeacherListDto;
import com.example.back404.teamproject.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.TEACHER_API)
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    // 교사 목록 전체 조회
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<TeacherListDto>>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }
}