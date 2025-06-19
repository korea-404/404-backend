package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.constants.enums.Affiliation;
import com.example.back404.teamproject.common.constants.enums.StudentStatus;
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

    @Column(name = "student_username", unique = true, nullable = false)
    private String username;

    @Column(name = "student_password", nullable = false)
    private String password;

    @Column(name = "student_number", unique = true, nullable = false)
    private String studentNumber;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(name = "student_grade", nullable = false)
    private int grade;

    @Column(name = "student_email", nullable = false)
    private String email;

    @Column(name = "student_phone", nullable = false)
    private String phoneNumber;

    @Column(name = "student_birth", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_affiliation", nullable = false)
    private Affiliation affiliation;

    @Column(name = "student_admission_year", nullable = false)
    private int admissionYear;

    @Column(name = "student_gender", nullable = false)
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_status", nullable = false)
    private StudentStatus status;

    public void update(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
