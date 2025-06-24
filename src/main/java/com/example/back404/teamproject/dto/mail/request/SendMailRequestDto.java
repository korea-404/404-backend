package com.example.back404.teamproject.dto.mail.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailRequestDto {
    @NotBlank @Email
    private String email;
}

