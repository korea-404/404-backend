package com.example.back404.teamproject.common.constants.enums;

import lombok.Getter;

@Getter
public enum SubjectStatus {
    approved("승인 완료"),
    pending("승인 대기"),
    rejected("승인 거절");

    private final String description;

    SubjectStatus(String description) {
        this.description = description;
    }
}