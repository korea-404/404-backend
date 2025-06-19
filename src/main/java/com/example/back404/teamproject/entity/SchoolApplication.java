package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
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

    @Column(name = "school_code", nullable = false, unique = true)
    private Integer schoolCode;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    public void approve() {
        this.status = ApplicationStatus.APPROVED;
    }

    public void reject() {
        this.status = ApplicationStatus.REJECTED;
    }

    public enum ApplicationStatus {
        PENDING, APPROVED, REJECTED
    }
}