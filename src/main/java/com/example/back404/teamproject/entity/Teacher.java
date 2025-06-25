package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.TeacherStatus;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private TeacherStatus status;

    public void setStatus(TeacherStatus status) {
        this.status = status;
    }
}
