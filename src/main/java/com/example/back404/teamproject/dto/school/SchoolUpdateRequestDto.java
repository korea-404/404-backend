package com.example.back404.teamproject.dto.school;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolUpdateRequestDto {
    private String schoolAddress;
    private String schoolContactNumber;
    private String schoolAdminName;
    private String schoolAdminPhoneNumber;
    private String schoolAdminEmail;
}
