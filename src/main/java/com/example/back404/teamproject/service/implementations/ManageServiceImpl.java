package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.constants.enums.StudentStatus;
import com.example.back404.teamproject.common.constants.enums.TeacherStatus;
import com.example.back404.teamproject.dto.student.request.StudentStatusUpdateRequestDto;
import com.example.back404.teamproject.dto.teacher.request.TeacherStatusUpdateRequestDto;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.entity.Teacher;
import com.example.back404.teamproject.repository.StudentRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.ManageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageServiceImpl implements ManageService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public ResponseDto<?> updateTeacherStatus(TeacherStatusUpdateRequestDto dto) {
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("교사를 찾을 수 없습니다."));

        try {
            TeacherStatus status = TeacherStatus.valueOf(dto.getStatus().toUpperCase());
            teacher.setStatus(status);
            return ResponseDto.setSuccess("교사 상태 변경 완료", null);
        } catch (IllegalArgumentException e) {
            return ResponseDto.setFailed("유효하지 않은 교사 상태입니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> updateStudentStatus(StudentStatusUpdateRequestDto dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("학생을 찾을 수 없습니다."));

        try {
            StudentStatus status = StudentStatus.valueOf(dto.getStatus().toUpperCase());
            student.setStatus(status);
            return ResponseDto.setSuccess("학생 상태 변경 완료", null);
        } catch (IllegalArgumentException e) {
            return ResponseDto.setFailed("유효하지 않은 학생 상태입니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
        return ResponseDto.setSuccess("교사 삭제 완료", null);
    }

    @Override
    @Transactional
    public ResponseDto<?> deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return ResponseDto.setSuccess("학생 삭제 완료", null);
    }
}