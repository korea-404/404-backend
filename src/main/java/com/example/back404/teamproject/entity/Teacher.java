package com.example.back404.teamproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teacher")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    private String id;

    @Column(name = "school_id", nullable = false)
    private Long schoolId;

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
    private TeacherStatus status;

    public void update(String name, String email, String phoneNumber, String subject) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subject = subject;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}







//package com.example.back404.teamproject.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "teacher")
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@Builder
//public class Teacher {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "teacher_id")
//    private Long id;
//
//    @Column(name = "teacher_name", nullable = false)
//    private String name;
//}