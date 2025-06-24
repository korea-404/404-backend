package com.example.back404.teamproject.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolSignUpRequestDto {
    @NotBlank private Integer schoolCode;
    @NotBlank private String username;
    @NotBlank private String password;
    @NotBlank private String name;
    @NotBlank @Email private String email;
    @NotBlank private String birthDate;
    @NotBlank private String phoneNumber;
}