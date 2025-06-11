package com.example.back404.teamproject.dto.user.response;

import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.entity.Teacher;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class GetUserResponseDto {

    private String userType;          // STUDENT / TEACHER / ADMIN
    private String username;          // 로그인용 ID
    private String name;              // 사용자 이름
    private String email;             // 이메일 주소
    private String phoneNumber;       // 전화번호
    private LocalDateTime createdAt;  // 가입일

    // Student용 생성자
    public GetUserResponseDto(Student student) {
        this.userType = "STUDENT";
        this.username = student.getUsername();
        this.name = student.getName();
        this.email = student.getEmail();
        this.phoneNumber = student.getPhoneNumber();
        this.createdAt = student.getCreatedAt();
    }

    // Teacher용 생성자
    public GetUserResponseDto(Teacher teacher) {
        this.userType = "TEACHER";
        this.username = teacher.getUsername();
        this.name = teacher.getName();
        this.email = teacher.getEmail();
        this.phoneNumber = teacher.getPhoneNumber();
        this.createdAt = teacher.getCreatedAt();
    }

    // 관리자(School)용 생성자
    public GetUserResponseDto(School school) {
        this.userType = "ADMIN";
        this.username = "admin@" + school.getSchoolCode(); // 관리자용 가상의 username
        this.name = school.getSchoolAdminName();
        this.email = school.getSchoolAdminEmail();
        this.phoneNumber = school.getSchoolAdminPhoneNumber();
        this.createdAt = school.getCreatedAt();
    }
}

