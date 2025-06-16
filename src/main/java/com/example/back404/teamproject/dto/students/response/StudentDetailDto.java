package com.example.back404.teamproject.dto.students.response;

import com.example.back404.teamproject.common.constants.enums.StudentStatus;
import com.example.back404.teamproject.common.constants.enums.Affiliation;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class StudentDetailDto {
    private String id;
    private String name;
    private String grade;
    private String studentNumber;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private Affiliation affiliation;
    private StudentStatus status;
    private int admissionYear;
}