package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.DayOfWeek; // 자바 표준 DayOfWeek 사용

@Entity
@Table(name = "lecture")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Lecture extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "lecture_period", nullable = false)
    private Integer period;

    @Column(name = "lecture_allowed_grade", nullable = false)
    private String allowedGrade;

    @Column(name = "lecture_max_enrollment", nullable = false)
    private Integer maxEnrollment;


     // 강의의 정보(담당 교사, 시간, 최대 수강 인원)를 수정
    public void updateInfo(Teacher teacher, DayOfWeek dayOfWeek, int period, int maxEnrollment) {
        this.teacher = teacher;
        this.dayOfWeek = dayOfWeek;
        this.period = period;
        this.maxEnrollment = maxEnrollment;
    }
}