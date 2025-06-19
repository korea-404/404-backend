package com.example.back404.teamproject.dto.school;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolInfoUpdateRequestDto {
    private String schoolName;
    private String schoolAddress;
    private String schoolContactNumber;

    private String currentPassword;
    private String newPassword;
}
