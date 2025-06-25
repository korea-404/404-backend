package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.CourseRegistrationStatus;
import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_registration")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CourseRegistration extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private CourseRegistrationStatus status = CourseRegistrationStatus.CONFIRMED;
}
