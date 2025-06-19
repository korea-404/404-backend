package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name = "course_registration_academic_year", nullable = false)
    private Integer academicYear;

    @Column(name = "course_registration_semester", nullable = false)
    private String semester;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_registration_approval_status", nullable = false)
    private CourseRegistrationApprovalStatus approvalStatus = CourseRegistrationApprovalStatus.PENDING;

    @Column(name = "course_registration_approval_date")
    private LocalDateTime approvalDate;

    // 수강 신청을 승인
    public void approve() {
        if (this.approvalStatus != CourseRegistrationApprovalStatus.PENDING) {
            throw new IllegalStateException("대기 상태인 수강 신청만 승인할 수 있습니다.");
        }
        this.approvalStatus = CourseRegistrationApprovalStatus.APPROVED;
        this.approvalDate = LocalDateTime.now();
    }

    // 수강 신청을 거절
    public void reject() {
        if (this.approvalStatus != CourseRegistrationApprovalStatus.PENDING) {
            throw new IllegalStateException("대기 상태인 수강 신청만 거절할 수 있습니다.");
        }
        this.approvalStatus = CourseRegistrationApprovalStatus.REJECTED;
        this.approvalDate = LocalDateTime.now();
    }
}