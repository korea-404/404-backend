package com.example.back404.teamproject.dto.auth;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolSignUpRequestDto {
    private Integer schoolCode;
    private String schoolName;
    private String schoolAddress;
    private String schoolContactNumber;
    private String schoolPassword;
    private String schoolAdminName;
    private String schoolAdminPhoneNumber;
    private String schoolAdminEmail;
    private LocalDate applicationStartedDay;
    private LocalDate applicationLimitedDay;
}

