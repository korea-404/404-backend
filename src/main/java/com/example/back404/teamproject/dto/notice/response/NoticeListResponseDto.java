package com.example.back404.teamproject.dto.notice.response;

import com.example.back404.teamproject.common.enums.NoticeTargetAudience;
import com.example.back404.teamproject.entity.Notice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeListResponseDto {
    private Long id;
    private String title;
    private NoticeTargetAudience targetAudience;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createAt;

    public static NoticeListResponseDto from(Notice notice) {
        return NoticeListResponseDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .targetAudience(notice.getTargetAudience())
                .startDate(notice.getStartDate())
                .endDate(notice.getEndDate())
                .createAt(notice.getCreateAt())
                .build();
    }
}