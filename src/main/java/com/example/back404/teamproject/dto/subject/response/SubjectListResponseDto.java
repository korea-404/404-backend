package com.example.back404.teamproject.dto.subject.response;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.enums.SubjectStatus;
import com.example.back404.teamproject.entity.Subject;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubjectListResponseDto {
    private String id;
    private String name;
    private String grade;
    private String semester;
    private SubjectAffiliation affiliation;
    private SubjectStatus status;
    private int maxEnrollment;

    public static SubjectListResponseDto from(Subject subject) {
        return SubjectAffiliation.builder()
                .id(subject.getId())
                .name(subject.getName())
                .grade(subject.getGrade())
                .semester(subject.getSemester())
                .subjectAffiliation(subject.getSubjectAffiliation())
                .subjectStatus(subject.getSubjectStatus())
                .maxEnrollment(subject.getMaxEnrollment())
                .build();
    }
}
