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
public class SchoolApplication extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolApplicationId;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private SchoolApplicationStatus schoolApplicationStatus = SchoolApplicationStatus.PENDING;

    private String schoolName;
    private String schoolAddress;
    private String schoolContactNumber;
    private String schoolAdminName;
    private String schoolAdminPhoneNumber;
    private String schoolAdminEmail;

    public enum SchoolApplicationStatus {
        PENDING, APPROVED, REJECTED
    }
}