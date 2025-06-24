package com.example.back404.teamproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CourseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String subjectName;

    @Column(nullable = false)
    private String grade;

    @Column(nullable = false)
    private String semester;

    @Column(nullable = false)
    private boolean completed;
}
