package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.constants.ResponseMessage;
import com.example.back404.teamproject.dto.ResponseDto;
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
    public ResponseDto<List<StudentListDto>> getStudentList(String name) {
        List<Student> students;
        if (name == null || name.isBlank()) {
            students = studentRepository.findAll();
        } else {
            students = studentRepository.findByNameContaining(name);
        }

        List<StudentListDto> dto = students.stream()
                .map(student -> StudentListDto.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .grade(student.getGrade())
                        .studentNumber(student.getStudentNumber())
                        .email(student.getEmail())
                        .build())
                .collect(Collectors.toList());
        return ResponseDto.setSuccess(ResponseMessage.GET_TEACHER_LIST_SUCCESS, dto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<StudentDetailDto> getStudentDetail(String studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_STUDENT + ":" + studentId));

        StudentDetailDto dto = StudentDetailDto.builder()
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
        return ResponseDto.setSuccess(ResponseMessage.GET_STUDENT_DETAIL_SUCCESS, dto);
    }
}