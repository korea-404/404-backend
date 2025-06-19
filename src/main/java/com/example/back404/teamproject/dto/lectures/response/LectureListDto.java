package com.example.back404.teamproject.dto.lectures.response;

import com.example.back404.teamproject.common.constants.enums.LectureDayOfWeek;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureListDto {
    private Long lectureId;
    private String subjectName;
    private String teacherName;
    private LectureDayOfWeek dayOfWeek;
    private int period;
}