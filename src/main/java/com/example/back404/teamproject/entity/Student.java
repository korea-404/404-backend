package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "student")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Student extends BaseTimeEntity {

    @Id
    @Column(name = "student_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(name = "student_username", unique = true, nullable = false)
    private String username;

    @Column(name = "student_password", nullable = false)
    private String password;

    @Column(name = "student_number", unique = true, nullable = false)
    private String studentNumber;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(name = "student_grade", nullable = false)
    private String grade;

    @Column(name = "student_email", unique = true, nullable = false)
    private String email;

    @Column(name = "student_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "student_birth_date", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_affiliation", nullable = false)
    private Affiliation affiliation;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_status", nullable = false)
    private StudentStatus status = StudentStatus.PENDING;

    @Column(name = "student_admission_year", nullable = false)
    private Integer admissionYear;

    public enum Affiliation {
        LIBERAL_ARTS, NATURAL_SCIENCES
    }

    // 학생의 상태를 명확하게 표현하도록 Enum을 수정
    public enum StudentStatus {
        PENDING,   // 승인 대기
        APPROVED,  // 재학
        ON_LEAVE,  // 휴학
        GRADUATED  // 졸업
    }

    //== 비즈니스 로직 (정보 및 상태 변경 메서드) ==//

    // 학생의 개인 정보(이메일, 전화번호)를 수정
    public void updateInfo(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // 비밀번호를 변경
    public void changePassword(String newPassword) {
        // 실제로는 암호화 로직이 포함되어야 합니다.
        this.password = newPassword;
    }

    // 학생의 가입을 승인하여 '재학' 상태로 변경
    public void approve() {
        if (this.status != StudentStatus.PENDING) {
            throw new IllegalStateException("승인 대기 상태의 학생만 승인할 수 있습니다.");
        }
        this.status = StudentStatus.APPROVED;
    }

    // 학생을 다음 학년으로 진급
    public void promote(String newGrade) {
        this.grade = newGrade;
    }

    // 학생을 '휴학' 상태로 변경
    public void takeLeave() {
        if (this.status != StudentStatus.APPROVED) {
            throw new IllegalStateException("재학 중인 학생만 휴학할 수 있습니다.");
        }
        this.status = StudentStatus.ON_LEAVE;
    }

    // '휴학' 상태의 학생을 '재학' 상태로 복학
    public void reinstate() {
        if (this.status != StudentStatus.ON_LEAVE) {
            throw new IllegalStateException("휴학 중인 학생만 복학할 수 있습니다.");
        }
        this.status = StudentStatus.APPROVED;
    }

    // 학생을 '졸업' 상태로 변경
    public void graduate() {
        if (this.status == StudentStatus.GRADUATED) {
            return; // 이미 졸업한 경우 변경하지 않음
        }
        this.status = StudentStatus.GRADUATED;
    }
}