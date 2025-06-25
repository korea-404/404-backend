package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.NoticeTargetAudience;
import com.example.back404.teamproject.entity.datetime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "notice")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Notice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "school_id", nullable = false)
    private Long schoolId;

    @Column(name = "notice_title", nullable = false)
    private String title;

    @Column(name = "notice_content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "notice_target_audience", nullable = false)
    private NoticeTargetAudiene targetAudience;

    @Column(name = "notice_start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "notice_end_date", nullable = false)
    private LocalDate endDate;

    public void update(String title, String content, NoticeTargetAudience targetAudience
                        LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.content = content;
        this.targetAudience = targetAudience;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
