package com.example.back404.teamproject.dto.students.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentListDto {
    private String id;
    private String name;
    private String grade;
    private String studentNumber;
    private String email;
}