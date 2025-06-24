package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.dto.student.request.StudentStatusUpdateRequestDto;
import com.example.back404.teamproject.dto.teacher.request.TeacherStatusUpdateRequestDto;
import com.example.back404.teamproject.service.ManageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manage")
@RequiredArgsConstructor
public class ManageController {

    private final ManageService manageService;

    @PutMapping("/teacher/status")
    public ResponseEntity<?> updateTeacherStatus(@RequestBody @Valid TeacherStatusUpdateRequestDto dto) {
        return ResponseEntity.ok(manageService.updateTeacherStatus(dto));
    }

    @PutMapping("/student/status")
    public ResponseEntity<?> updateStudentStatus(@RequestBody @Valid StudentStatusUpdateRequestDto dto) {
        return ResponseEntity.ok(manageService.updateStudentStatus(dto));
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        return ResponseEntity.ok(manageService.deleteTeacher(id));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(manageService.deleteStudent(id));
    }
}