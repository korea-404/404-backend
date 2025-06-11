package com.example.back404.teamproject.common.constants.enums;

import lombok.Getter;

@Getter
public enum StudentStatus {
    PENDING("승인 대기"),
    APPROVED("재학"),
    ON_LEAVE("휴학"),
    GRADUATED("졸업");

    private final String description;

    StudentStatus(String description) {
        this.description = description;
    }
}