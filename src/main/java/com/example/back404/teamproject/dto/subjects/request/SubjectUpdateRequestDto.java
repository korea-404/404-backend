package com.example.back404.teamproject.dto.subjects.request;

import com.example.back404.teamproject.common.constants.enums.Affiliation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectUpdateRequestDto {

    @NotBlank
    private String subjectName;

    @NotBlank
    private String grade;

    @NotBlank
    private String semester;

    @NotNull
    private Affiliation affiliation;

    @NotNull
    private Integer maxEnrollment;
}