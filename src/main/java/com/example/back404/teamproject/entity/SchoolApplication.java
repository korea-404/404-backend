package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.constants.enums.SchoolStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String schoolName;
    private String schoolAddress;
    private String schoolContactNumber;

    private String schoolAdminUsername;
    private String schoolAdminPassword;
    private String schoolAdminName;
    private String schoolAdminBirthDate;
    private String schoolAdminPhoneNumber;
    private String schoolAdminEmail;

    @Column(nullable = false)
    private String schoolEmail;

    private String applicationStartedDay;
    private String applicationLimitedDay;

    @Column(name = "school_code", nullable = false, unique = true)
    private Integer schoolCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private SchoolStatus status = SchoolStatus.PENDING;

    public void updateStatus(SchoolStatus newStatus) {
        this.status = newStatus;
    }

    public Long getSchoolApplicationId() {
        return this.id;
    }
}
