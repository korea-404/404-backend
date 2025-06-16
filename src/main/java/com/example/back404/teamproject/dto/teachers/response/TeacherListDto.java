package com.example.back404.teamproject.dto.teachers.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherListDto {
    private String id;
    private String name;
    private String subject;
    private String email;
    private String phoneNumber;
}