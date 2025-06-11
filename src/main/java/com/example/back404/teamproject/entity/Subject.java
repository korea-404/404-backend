package com.example.back404.teamproject.entity;

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
    @Column(name = "subject_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "subject_grade", nullable = false)
    private String grade;

    @Column(name = "subject_semester", nullable = false)
    private String semester;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_affiliation", nullable = false)
    private Affiliation affiliation;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_category", nullable = false)
    private SubjectCategory category;

    @Column(name = "subject_max_enrollment", nullable = false)
    private Integer maxEnrollment;

    public enum Affiliation {
        LIBERAL_ARTS, NATURAL_SCIENCES
    }

    public enum SubjectCategory {
        COMPLETED,    // 이수 과목
        NOT_SELECTED  // 미선택 과목 (과목 개설용 템플릿)
    }


    // 과목의 기본 정보(과목명, 대상 학년, 학기, 최대 수강 인원)를 수정
    public void updateInfo(String subjectName, String grade, String semester, int maxEnrollment) {
        this.subjectName = subjectName;
        this.grade = grade;
        this.semester = semester;
        this.maxEnrollment = maxEnrollment;
    }

    // 과목을 '이수 과목' 상태로 선택(활성화)
    public void select() {
        this.category = SubjectCategory.COMPLETED;
    }

    // 과목을 '미선택' 상태로 변경
    public void deselect() {
        this.category = SubjectCategory.NOT_SELECTED;
    }
}