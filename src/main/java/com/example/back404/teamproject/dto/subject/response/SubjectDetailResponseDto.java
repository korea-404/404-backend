package com.example.back404.teamproject.dto.subject.response;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.enums.SubjectStatus;
import com.example.back404.teamproject.entity.Subject;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SubjectDetailResponseDto {
    private String id;
    private String name;
    private String grade;
    private String semester;
    private SubjectAffiliation affiliation;
    private SubjectStatus status;
    private int maxEnrollment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static SubjectDetailResponseDto from(Subject subject) {
        return SubjectDetailResponseDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .grade(subject.getGrade())
                .semester(subject.getSemester())
                .subjectAffiliation(subject.getSubjectAffiliation())
                .subjectStatus(subject.getSubjectStatus())
                .maxEnrollment(subject.getMaxEnrollment())
                .createdAt(subject.getCreatedAt())
                .updatedAt(subject.getUpdatedAt())
                .build();
    }
}