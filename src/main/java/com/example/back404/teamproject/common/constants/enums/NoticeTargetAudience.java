package com.example.back404.teamproject.common.constants.enums;

import lombok.Getter;

@Getter
public enum NoticeTargetAudience {
    ALL("전체"),
    STUDENT("학생"),
    TEACHER("교사");

    private final String description;

    NoticeTargetAudience(String description) {
        this.description = description;
    }
}