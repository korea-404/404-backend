package com.example.back404.teamproject.dto.teacher.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherStatusUpdateRequestDto {
    private Long teacherId;
    private String status;
}