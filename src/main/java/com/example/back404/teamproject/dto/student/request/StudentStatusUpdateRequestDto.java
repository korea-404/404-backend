package com.example.back404.teamproject.dto.student.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentStatusUpdateRequestDto {
    private Long studentId;
    private String status;
}
