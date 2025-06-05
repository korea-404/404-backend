package com.example.korea_sleepTech_PT.teamproject.entity;

import com.example.korea_sleepTech_PT.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher extends BaseTimeEntity {

    @Id
    private String teacherId;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ENROLLED;

    public enum Status {
        ENROLLED, NOT_ENROLLED
    }
}

