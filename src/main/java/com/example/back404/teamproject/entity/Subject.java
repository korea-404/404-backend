package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.constants.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.constants.enums.SubjectStatus;
import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subject")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Subject extends BaseTimeEntity {

    @Id
    @Column(name = "subject_id")
    private String subjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School schoolId;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "subject_grade", nullable = false)
    private String grade;

    @Column(name = "subject_semester", nullable = false)
    private String semester;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_affiliation", nullable = false)
    private SubjectAffiliation affiliation;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_status", nullable = false)
    private SubjectStatus status;

    @Column(name = "subject_max_enrollment", nullable = false)
    private Integer maxEnrollment;
}