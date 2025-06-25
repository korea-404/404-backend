package com.example.back404.teamproject.dto.auth.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindIdResponseDto {
    private String username;
    private String maskedEmail;

    public static FindIdResponseDto of(String username, String email) {
        String maskedEmail = maskEmail(email);
        return FindIdResponseDto.builder()
                .username(username)
                .maskedEmail(maskedEmail)
                .build();
    }

    private static String maskEmail(String email) {
        String[] parts = email.split("@");
        String localPart = parts[0];
        String domain = parts[1];

        if(localPart.length() >= 2) {
            return localPart.charAt(0) + "*@" + domain;
        }else{
            return localPart.substring(0, 2) + "*".repeat(localPart.length() - 2) +  "@" + domain;
        }
    }
}
