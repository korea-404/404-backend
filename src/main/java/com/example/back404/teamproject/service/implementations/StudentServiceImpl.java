package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.constants.ResponseDto;
import com.example.back404.teamproject.dto.students.response.StudentDetailDto;
import com.example.back404.teamproject.dto.students.response.StudentListDto;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.repository.StudentRepository;
import com.example.back404.teamproject.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<StudentListDto>> getAllStudents(String name) {
        List<Student> students = (name == null) ?
                studentRepository.findAll() :
                studentRepository.findByNameContaining(name);

        List<StudentListDto> dtoList = students.stream()
                .map(student -> StudentListDto.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .grade(student.getGrade())
                        .studentNumber(student.getStudentNumber())
                        .email(student.getEmail())
                        .build())
                .collect(Collectors.toList());
        return ResponseDto.setSuccess("학생 목록 조회 성공", dtoList);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<StudentDetailDto> getStudentById(String studentId) {
        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 학생입니다."));

            StudentDetailDto responseData = StudentDetailDto.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .grade(student.getGrade())
                    .studentNumber(student.getStudentNumber())
                    .email(student.getEmail())
                    .phoneNumber(student.getPhoneNumber())
                    .birthDate(student.getBirthDate())
                    .affiliation(student.getAffiliation())
                    .status(student.getStatus())
                    .admissionYear(student.getAdmissionYear())
                    .build();

            return ResponseDto.setSuccess("학생 상세 정보 조회 성공", responseData);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }
}