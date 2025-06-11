package com.example.back404.teamproject.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.catalina.User;

@Getter
@AllArgsConstructor
public class UserSignInResponseDto {
    private String token; // jwt 토큰
    private User user;
    private int exprTime; // expire + time: (토큰) 만료 시간
}
