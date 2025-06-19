package com.example.back404.teamproject.dto.subjects.response;

import com.example.back404.teamproject.common.constants.enums.Affiliation;
import com.example.back404.teamproject.common.constants.enums.SubjectStatus;
import lombok.*;


@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SubjectGetResponseDto {
    private String subjectId;
    private Long schoolId;
    private String subjectName;
    private String grade;
    private String semester;
    private Affiliation affiliation;
    private SubjectStatus status;
    private Integer maxEnrollment;
}
