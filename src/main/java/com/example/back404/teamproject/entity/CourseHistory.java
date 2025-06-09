package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CourseHistory extends BaseTimeEntity {

    @Id
    @Column(name = "course_history_id")
    private Integer id; // DDL에 AUTO_INCREMENT가 없으므로 @GeneratedValue 제거

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Column(name = "course_history_academic_year", nullable = false)
    private Integer academicYear;

    @Column(name = "course_history_semester", nullable = false)
    private String semester;

    @Column(name = "course_history_score") // 성적은 나중에 입력될 수 있으므로 nullable
    private String score;

    //== 비즈니스 로직 (정보 변경 메서드) ==//

    // 성적을 갱신
    public void updateScore(String newScore) {
        this.score = newScore;
    }
}