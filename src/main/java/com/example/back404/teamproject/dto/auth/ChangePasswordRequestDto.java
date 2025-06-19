package com.example.back404.teamproject.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequestDto {
    private String currentPassword;
    private String newPassword;
}
