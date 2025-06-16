package com.example.back404.teamproject.dto.subjects.response;

import com.example.back404.teamproject.common.constants.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.constants.enums.SubjectStatus;
import lombok.*;


@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SubjectDetailDto {
    private String subjectId;
    private String schoolId;
    private String subjectName;
    private String grade;
    private String semester;
    private SubjectAffiliation affiliation;
    private SubjectStatus status;
    private Integer maxEnrollment;
}
