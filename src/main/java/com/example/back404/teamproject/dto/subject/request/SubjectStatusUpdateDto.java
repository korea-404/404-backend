package com.example.back404.teamproject.dto.subject.request;

import com.example.back404.teamproject.common.enums.SubjectStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectStatusUpdateDto {
    @NotNull(message = "상태는 필수입니다.")
    private SubjectStatus status;
}
