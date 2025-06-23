package com.example.back404.teamproject.dto.subjects.response;

import com.example.back404.teamproject.common.constants.enums.Affiliation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectListGetResponseDto {
    private String subjectName;
    private String grade;
    private String semester;
    private Affiliation affiliation;
}