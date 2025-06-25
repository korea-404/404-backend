package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.enums.SubjectStatus;
import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subject")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Subject extends BaseTimeEntity {

    @Id
    private String subjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    private String subjectName;
    private String grade;
    private String semester;

    @Enumerated(EnumType.STRING)
    private SubjectAffiliation affiliation;

    @Enumerated(EnumType.STRING)
    private SubjectStatus status;

    private Integer maxEnrollment;

    private String description;
}
