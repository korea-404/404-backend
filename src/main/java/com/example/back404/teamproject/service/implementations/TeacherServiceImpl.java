package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.constants.ResponseMessage;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.teachers.response.TeacherListDto;
import com.example.back404.teamproject.entity.Teacher;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public ResponseDto<List<TeacherListDto>> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherListDto> dto = teachers.stream()
                .map(teacher -> TeacherListDto.builder()
                        .id(teacher.getId())
                        .name(teacher.getName())
                        .subject(teacher.getSubject())
                        .email(teacher.getEmail())
                        .phoneNumber(teacher.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
        return ResponseDto.setSuccess(ResponseMessage.GET_TEACHER_LIST_SUCCESS, dto);
    }
}