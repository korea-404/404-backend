package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teacher")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Teacher extends BaseTimeEntity {

    @Id
    @Column(name = "teacher_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(name = "teacher_username", unique = true, nullable = false)
    private String username;

    @Column(name = "teacher_password", nullable = false)
    private String password;

    @Column(name = "teacher_name", nullable = false)
    private String name;

    @Column(name = "teacher_email", unique = true, nullable = false)
    private String email;

    @Column(name = "teacher_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "teacher_subject", nullable = false)
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_status", nullable = false)
    private TeacherStatus status = TeacherStatus.PENDING;

    // Enum 이름을 TeacherStatus로 명확화
    public enum TeacherStatus {
        PENDING,  // 승인 대기
        APPROVED, // 승인 (재직)
        ON_LEAVE, // 휴직
        RETIRED   // 퇴직
    }

    //== 비즈니스 로직 (정보 및 상태 변경 메서드) ==//

    // 교사의 개인 정보(이메일, 전화번호, 담당 과목)를 수정
    public void updateInfo(String email, String phoneNumber, String subject) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subject = subject;
    }

    // 비밀번호를 변경
    public void changePassword(String newPassword) {
        // 실제로는 암호화 로직을 포함해야 합니다.
        this.password = newPassword;
    }

    // 가입 신청을 승인하여 '재직' 상태로 변경
    public void approve() {
        if (this.status != TeacherStatus.PENDING) {
            throw new IllegalStateException("승인 대기 상태의 교사만 승인할 수 있습니다.");
        }
        this.status = TeacherStatus.APPROVED;
    }

    // '재직' 상태의 교사를 '휴직' 상태로 변경
    public void takeLeave() {
        if (this.status != TeacherStatus.APPROVED) {
            throw new IllegalStateException("재직 중인 교사만 휴직 처리할 수 있습니다.");
        }
        this.status = TeacherStatus.ON_LEAVE;
    }

    // '휴직' 상태의 교사를 '재직' 상태로 복직
    public void reinstate() {
        if (this.status != TeacherStatus.ON_LEAVE) {
            throw new IllegalStateException("휴직 중인 교사만 복직 처리할 수 있습니다.");
        }
        this.status = TeacherStatus.APPROVED;
    }

    // 교사를 '퇴직' 상태로 변경
    public void retire() {
        this.status = TeacherStatus.RETIRED;
    }
}