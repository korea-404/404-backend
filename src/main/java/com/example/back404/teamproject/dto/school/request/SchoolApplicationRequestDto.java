package com.example.back404.teamproject.dto.school.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolApplicationRequestDto {

    private String schoolName;
    private String schoolAddress;
    private String schoolContactNumber;

    private String schoolAdminUsername;
    private String schoolAdminPassword;
    private String schoolAdminName;
    private String schoolAdminBirthDate;
    private String schoolAdminPhoneNumber;
    private String schoolAdminEmail;

    private String schoolEmail;

    private String applicationStartedDay;
    private String applicationLimitedDay;

    private Integer schoolCode;
}
