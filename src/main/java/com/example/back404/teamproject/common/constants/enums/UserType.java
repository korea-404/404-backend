package com.example.back404.teamproject.common.constants.enums;

public enum UserType {
    STUDENT("학생"),
    TEACHER("교사"),
    SCHOOL_ADMIN("학교 관리자");

    private final String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

