package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.enums.StudentStatus;
import com.example.back404.teamproject.dto.auth.request.LoginRequestDto;
import com.example.back404.teamproject.dto.student.request.StudentSignUpRequestDto;
import com.example.back404.teamproject.dto.student.request.StudentUpdateRequestDto;
import com.example.back404.teamproject.dto.student.response.StudentInfoResponseDto;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void signUp(StudentSignUpRequestDto request) {
        if (studentRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        LocalDate birth;
        try {
            birth = LocalDate.parse(request.getBirthDate());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("생년월일 형식이 잘못되었습니다. (예: 2006-09-01)");
        }

        Student student = Student.builder()
                .id(request.getUsername())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .studentNumber(request.getStudentNumber())
                .name(request.getName())
                .grade(request.getGrade())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .birthDate(birth)
                .affiliation(request.getAffiliation())
                .status(StudentStatus.PENDING)
                .admissionYear(request.getAdmissionYear())
                .gender(request.getGender())
                .build();

        studentRepository.save(student);
    }

    public Student login(LoginRequestDto request) {
        Student student = studentRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
            return student;
        }

    public StudentInfoResponseDto getStudentInfo(String studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("학생을 찾을 수 없습니다."));
        return StudentInfoResponseDto.from(student);
    }

    public void updateStudentInfo(String studentId, StudentUpdateRequestDto request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("학생을 찾을 수 없습니다."));

        student.update(
                request.getName(),
                request.getPhoneNumber(),
                request.getEmail()
        );

        studentRepository.save(student);
    }

}
