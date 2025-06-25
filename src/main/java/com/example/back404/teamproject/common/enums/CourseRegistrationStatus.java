package com.example.back404.teamproject.common.enums;

public enum CourseRegistrationStatus {
    PENDING("대기"),
    CONFIRMED("확정"),
    CANCELED("취소");

    private final String description;

    CourseRegistrationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
