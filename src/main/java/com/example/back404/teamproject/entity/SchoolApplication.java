package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "school_application")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SchoolApplication extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_application_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "school_application_status", nullable = false)
    private SchoolApplicationStatus schoolApplicationStatus = SchoolApplicationStatus.PENDING;

    @Column(name = "school_name", nullable = false)
    private String schoolName;

    @Column(name = "school_address", nullable = false)
    private String schoolAddress;

    @Column(name = "school_contact_number", nullable = false)
    private String schoolContactNumber;

    @Column(name = "school_admin_name", nullable = false)
    private String schoolAdminName;

    @Column(name = "school_admin_phone_number", nullable = false)
    private String schoolAdminPhoneNumber;

    @Column(name = "school_admin_email", nullable = false)
    private String schoolAdminEmail;

    public enum SchoolApplicationStatus {
        PENDING, APPROVED, REJECTED
    }


    // 신청을 승인 상태로 변경
    public void approve() {
        this.schoolApplicationStatus = SchoolApplicationStatus.APPROVED;
    }

    // 신청을 거절 상태로 변경
    public void reject() {
        this.schoolApplicationStatus = SchoolApplicationStatus.REJECTED;
    }
}