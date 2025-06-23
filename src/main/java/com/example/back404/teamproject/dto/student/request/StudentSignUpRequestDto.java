package com.example.back404.teamproject.dto.student.request;

import com.example.back404.teamproject.common.enums.StudentStatus;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class StudentSignUpRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String studentNumber;

    @NotBlank
    private String name;

    @Min(1) @Max(3)
    private int grade;

    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private String birthDate;

    @NotNull
    private SubjectAffiliation affiliation;

    @Min(2000)
    private int admissionYear;

    @Pattern(regexp = "male|female", message = "성별은 male 또는 female이어야 합니다.")
    private String gender;

    @NotNull
    private StudentStatus status;

}
