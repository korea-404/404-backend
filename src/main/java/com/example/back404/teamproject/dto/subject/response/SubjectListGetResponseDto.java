package com.example.back404.teamproject.dto.subject.response;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
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
    private SubjectAffiliation affiliation;
}