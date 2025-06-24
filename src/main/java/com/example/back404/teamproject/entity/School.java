package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.constants.enums.SchoolStatus;
import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "school")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class School extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long schoolId;

    @Column(nullable = false)
    private String schoolName;

    @Column(nullable = false)
    private String schoolAddress;

    @Column(nullable = false)
    private String schoolContactNumber;

    @Column(nullable = false, unique = true)
    private Integer schoolCode;

    @Column(nullable = false)
    private String schoolPassword;

    @Column(nullable = false, unique = true)
    private String schoolAdminUsername;

    @Column(nullable = false)
    private String schoolAdminPassword;

    @Column(nullable = false)
    private String schoolAdminName;

    @Column(nullable = false)
    private String schoolAdminPhoneNumber;

    @Column(nullable = false)
    private String schoolAdminEmail;

    @Column(nullable = false)
    private LocalDate schoolAdminBirthDate;

    @Column(nullable = false)
    private LocalDate applicationStartedDay;

    @Column(nullable = false)
    private LocalDate applicationLimitedDay;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SchoolStatus status;

    @Column(nullable = false)
    private String schoolEmail;

    // --- Setter methods for updatable fields ---

    public void setSchoolEmail(String email) {
        this.schoolEmail = email;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public void setSchoolContactNumber(String schoolContactNumber) {
        this.schoolContactNumber = schoolContactNumber;
    }

    public void setSchoolAdminPhoneNumber(String phoneNumber) {
        this.schoolAdminPhoneNumber = phoneNumber;
    }

    public void setSchoolAdminEmail(String email) {
        this.schoolAdminEmail = email;
    }

    public void setPassword(String password) {
        this.schoolPassword = password;
    }
}
