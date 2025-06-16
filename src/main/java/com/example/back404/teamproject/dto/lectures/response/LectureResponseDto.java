package com.example.back404.teamproject.dto.lectures.response;

import com.example.back404.teamproject.common.constants.enums.LectureDayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureResponseDto {
    private Long lectureId;
    private String subjectName;
    private String teacherName;
    private LectureDayOfWeek dayOfWeek;
    private Integer period;
    private String allowedGrade;
}
