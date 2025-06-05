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
public class SchoolApplication extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolApplicationId;

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
