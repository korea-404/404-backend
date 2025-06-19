package com.example.back404.teamproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController {

    // 관리자만 접근 가능
    @GetMapping("/api/v1/admin/feature")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminFeature() {
        return "관리자 전용 기능";
    }

    // 교사 또는 관리자만 접근 가능
    @GetMapping("/api/v1/teacher/feature")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public String getTeacherFeature() {
        return "교사 및 관리자 기능";
    }

    // 로그인한 모든 사용자가 접근 가능
    @GetMapping("/api/v1/common/feature")
    @PreAuthorize("isAuthenticated()")
    public String getCommonFeature() {
        return "로그인 사용자 공통 기능";
    }
}

