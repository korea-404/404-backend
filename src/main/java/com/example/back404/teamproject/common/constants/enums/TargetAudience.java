package com.example.back404.teamproject.common.constants.enums;

import lombok.Getter;

@Getter
public enum TargetAudience {
    ALL("전체"),
    STUDENT("학생"),
    TEACHER("교사");

    private final String description;

    TargetAudience(String description) {
        this.description = description;
    }
}