package com.example.back404.teamproject.common.constants.enums;

import lombok.Getter;

@Getter
public enum SchoolApplicationStatus {
    PENDING("대기"),
    APPROVED("승인"),
    REJECTED("거절");

    private final String description;

    SchoolApplicationStatus(String description) {
        this.description = description;
    }
}