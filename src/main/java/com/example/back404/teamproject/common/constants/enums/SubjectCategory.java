package com.example.back404.teamproject.common.constants.enums;

import lombok.Getter;

@Getter
public enum SubjectCategory {
    COMPLETED("선택 과목"),
    NOTSELECTED("미선택 과목");

    private final String description;

    SubjectCategory(String description) {
        this.description = description;
    }
}
