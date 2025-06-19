package com.example.back404.teamproject.dto.school;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolStatusUpdateRequestDto {

    @NotBlank(message = "상태 값을 입력하세요.")
    @Pattern(
            regexp = "PENDING|APPROVED|REJECTED",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "상태는 PENDING, APPROVED, REJECTED 중 하나여야 합니다."
    )
    private String status;
}

