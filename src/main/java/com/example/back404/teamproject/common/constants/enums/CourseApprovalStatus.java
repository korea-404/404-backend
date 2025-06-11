package com.example.back404.teamproject.common.constants.enums;

import lombok.Getter;

@Getter
public enum CourseApprovalStatus {
    PENDING("대기"),
    APPROVED("승인"),
    REJECTED("거절");

    private final String description;

    CourseApprovalStatus(String description) {
        this.description = description;
    }
}