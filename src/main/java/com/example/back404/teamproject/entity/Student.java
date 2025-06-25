package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.StudentStatus;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String studentNumber;

    private String name;

    private int grade;

    private String email;

    private String phoneNumber;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private SubjectAffiliation affiliation;

    private int admissionYear;

    private String gender;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    public void update(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }
}
