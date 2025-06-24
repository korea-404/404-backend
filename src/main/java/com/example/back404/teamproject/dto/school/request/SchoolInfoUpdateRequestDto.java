package com.example.back404.teamproject.dto.school.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolInfoUpdateRequestDto {
    private String phoneNumber;
    private String email;
    private String schoolEmail;
    private String schoolAddress;
    private String schoolContactNumber;

    public String getSchoolContactNumber() {
        return schoolContactNumber;
    }
}
