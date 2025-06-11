package com.example.back404.teamproject.common.constants.enums;

import lombok.Getter;

@Getter
public enum SubjectCategory {
    COMPLETED("이수 과목"),
    NOT_SELECTED("미선택 과목");

    private final String description;

    SubjectCategory(String description) {
        this.description = description;
    }
}