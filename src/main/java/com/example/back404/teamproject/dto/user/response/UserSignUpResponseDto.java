package com.example.back404.teamproject.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserSignUpResponseDto {
    User user;
}
