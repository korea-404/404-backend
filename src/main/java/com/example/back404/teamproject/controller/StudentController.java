package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.students.response.StudentDetailDto;
import com.example.back404.teamproject.dto.students.response.StudentListDto;
import com.example.back404.teamproject.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    // 학생 목록 조회 (이름 검색 )
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<StudentListDto>>> getStudentList(
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(studentService.getStudentList(name));
    }

    // 학생 상세 정보 조회
    @GetMapping("/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<StudentDetailDto>> getStudentDetail(
            @PathVariable String studentId) {
        return ResponseEntity.ok(studentService.getStudentDetail(studentId));
    }
}