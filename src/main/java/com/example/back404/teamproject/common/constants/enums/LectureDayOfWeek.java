package com.example.back404.teamproject.common.constants.enums;

import lombok.Getter;

@Getter
public enum LectureDayOfWeek {
    Monday("월요일"),
    Tuesday("화요일"),
    Wednesday("수요일"),
    Thursday("목요일"),
    Friday("금요일");

    private final String description;

    LectureDayOfWeek(String description) {
        this.description = description;
    }
}