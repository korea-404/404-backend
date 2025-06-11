package com.example.back404.teamproject.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword; // 비밀번호 확인
}
