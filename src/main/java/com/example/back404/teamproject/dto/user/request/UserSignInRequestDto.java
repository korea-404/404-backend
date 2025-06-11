package com.example.back404.teamproject.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInRequestDto {
    @NotNull
    private String email;

    @NotNull
    private String password;
}
