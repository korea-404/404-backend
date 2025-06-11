package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject extends BaseTimeEntity {

    @Id
    private String subjectId;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    private String subjectName;
    private String grade;
    private String semester;

    @Enumerated(EnumType.STRING)
    private Affiliation affiliation;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer maxEnrollment;

    public enum Affiliation {
        LIBERAL_ARTS, NATURAL_SCIENCES
    }

    public enum Category {
        COMPLETED, NOT_SELECTED
    }
}

