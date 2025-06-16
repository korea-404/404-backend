package com.example.back404.teamproject.dto.lectures.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.DayOfWeek;

@Getter
@NoArgsConstructor
public class LectureUpdateRequestDto {
    @NotBlank
    private String teacherId;

    @NotNull
    private DayOfWeek dayOfWeek;

    @NotNull
    @Min(1) @Max(8)
    private int period;

    @NotNull
    @Min(0)
    private int maxEnrollment;
}