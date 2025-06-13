package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.constants.enums.LectureDayOfWeek;
import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lecture")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Lecture extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long lectureId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School schoolId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacherId;

    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_day_of_week", nullable = false)
    private LectureDayOfWeek dayOfWeek;

    @Column(name = "lecture_period", nullable = false)
    private Integer period;

    @Column(name = "lecture_allowed_grade", nullable = false)
    private String allowedGrade;

    @Column(name = "lecture_max_enrollment", nullable = false)
    private Integer maxEnrollment;
}
