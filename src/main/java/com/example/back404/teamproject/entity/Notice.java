package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private TargetAudience targetAudience;

    private LocalDate startDate;
    private LocalDate endDate;

    public enum TargetAudience {
        ALL, STUDENT, TEACHER
    }
}

