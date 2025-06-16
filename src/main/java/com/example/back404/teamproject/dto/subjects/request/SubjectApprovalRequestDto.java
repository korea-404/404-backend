package com.example.back404.teamproject.dto.subjects.request;

import com.example.back404.teamproject.common.constants.enums.LectureDayOfWeek;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectApprovalRequestDto {
    @NotBlank
    private String teacherId;

    @NotNull
    private LectureDayOfWeek dayOfWeek;

    @NotNull
    @Min(1) @Max(10)
    private Integer period;

    @NotBlank
    private String allowedGrade;

    @NotNull
    @Min(0)
    private Integer maxEnrollment;
}