package com.example.back404.teamproject.dto.Teacher.response;

import com.example.back404.teamproject.common.enums.TeacherStatus;
import com.example.back404.teamproject.entity.Teacher;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherInfoResponseDto {
    private String id;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private String subject;
    private TeacherStatus status;
    private Long schoolId;

    public static TeacherInfoResponseDto from(Teacher teacher) {
        return TeacherInfoResponseDto.builder()
                .id(teacher.getId())
                .username(teacher.getUsername())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .phoneNumber(teacher.getPhoneNumber())
                .subject(teacher.getSubject())
                .status(teacher.getStatus())
                .schoolId(teacher.getSchoolId())
                .build();
    }
}