package com.example.back404.teamproject.dto._regex;

import com.example.back404.teamproject.common.constants.RegexConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegexDto {

    @NotBlank(message = "아이디는 필수입니다.")
    @Pattern(regexp = RegexConstants.USER_ID, message = "아이디는 4~12자의 영문/숫자를 사용해야 합니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(
            regexp = RegexConstants.PASSWORD,
            message = "비밀번호는 최소 8자 이상, 영문, 숫자, 특수문자 각각 하나 이상 포함"
    )
    private String password;

    @NotBlank
    private String confirmPassword; // 서비스에서 equals() 비교 필요

    @NotBlank
    @Pattern(regexp = RegexConstants.KOREAN_NAME, message = "이름은 한글 2 ~ 10자 이내")
    private String userName;

    // phoneNumber: 휴대폰 번호 양식이 올바르지 않습니다.
    // nickname: 닉네임은 2~10자 영문/숫자/한글만 사용 가능합니다.
    // gender, birthDate, region 등등

    @NotBlank
    @Email(message = "유효한 이메일 주소여야 합니다.") // 일반 정규표현식 사용 권장
    private String email;
}
