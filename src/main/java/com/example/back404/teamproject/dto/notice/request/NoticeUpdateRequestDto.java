package com.example.back404.teamproject.dto.notice.request;

import com.example.back404.teamproject.common.enums.NoticeTargetAudience;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class NoticeUpdateRequestDto {
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotNull(message = "대상은 필수입니다.")
    private NoticeTargetAudience targetAudience;

    @NotNull(message = "시작일은 필수입니다.")
    private LocalDate startDate;

    @NotNull(message = "종료일은 필수입니다")
    private LocalDate endDate;
}