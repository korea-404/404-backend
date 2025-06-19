package com.example.back404.teamproject.dto.subjects.request;

import com.example.back404.teamproject.common.constants.enums.SubjectStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectStatusUpdateDto {
    @NotNull
    private SubjectStatus status;
}
