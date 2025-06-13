package com.example.back404.teamproject.entity;

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
    @Column(name = "school_id")
    private String schoolId;

    @Column(name = "school_code", nullable = false, unique = true)
    private Integer schoolCode;

    @Column(name = "school_name", nullable = false)
    private String schoolName;

    @Column(name = "school_address", nullable = false)
    private String schoolAddress;

    @Column(name = "school_contact_number", nullable = false)
    private String schoolContactNumber;

    @Column(name = "school_password", nullable = false)
    private String schoolPassword;

    @Column(name = "school_admin_name", nullable = false)
    private String schoolAdminName;

    @Column(name = "school_admin_phone_number", nullable = false)
    private String schoolAdminPhoneNumber;

    @Column(name = "school_admin_email", nullable = false)
    private String schoolAdminEmail;

    @Column(name = "application_started_day", nullable = false)
    private LocalDate applicationStartedDay;

    @Column(name = "application_limited_day", nullable = false)
    private LocalDate applicationLimitedDay;


    // 학교의 기본 정보(주소, 연락처, 관리자 이름 등)를 수정
    public void updateSchoolInfo(String address, String contactNumber, String adminName, String adminPhoneNumber, String adminEmail) {
        this.schoolAddress = address;
        this.schoolContactNumber = contactNumber;
        this.schoolAdminName = adminName;
        this.schoolAdminPhoneNumber = adminPhoneNumber;
        this.schoolAdminEmail = adminEmail;
    }

    // 학교 관리자 비밀번호를 변경
    public void changePassword(String newPassword) {
        this.schoolPassword = newPassword;
    }

    // 수강 신청 기간을 수정
    public void updateApplicationPeriod(LocalDate startDate, LocalDate limitedDate) {
        this.applicationStartedDay = startDate;
        this.applicationLimitedDay = limitedDate;
    }
}