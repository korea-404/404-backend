package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.enums.CourseRegistrationStatus;
import com.example.back404.teamproject.common.enums.ErrorCode;
import com.example.back404.teamproject.dto.registration.request.CourseRegistrationRequestDto;
import com.example.back404.teamproject.dto.registration.response.CourseRegistrationResponseDto;
import com.example.back404.teamproject.entity.CourseRegistration;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.exception.CustomException;
import com.example.back404.teamproject.repository.CourseRegistrationRepository;
import com.example.back404.teamproject.repository.LectureRepository;
import com.example.back404.teamproject.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseRegistrationService {

    private final CourseRegistrationRepository courseRegistrationRepository;
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    @Transactional
    public void registerByEmail(String email, CourseRegistrationRequestDto requestDto) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        Lecture lecture = lectureRepository.findById(requestDto.getLectureId())
                .orElseThrow(() -> new CustomException(ErrorCode.LECTURE_NOT_FOUND));

        boolean alreadyRegistered = courseRegistrationRepository
                .existsByStudent_IdAndLecture_LectureId(student.getId(), requestDto.getLectureId());

        if (alreadyRegistered) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED);
        }

        if (lecture.getCourseRegistrations().size() >= lecture.getMaxEnrollment()) {
            throw new CustomException(ErrorCode.LECTURE_FULL);
        }

        CourseRegistration registration = CourseRegistration.builder()
                .student(student)
                .lecture(lecture)
                .status(CourseRegistrationStatus.CONFIRMED)
                .build();

        courseRegistrationRepository.save(registration);
    }

    @Transactional(readOnly = true)
    public List<CourseRegistrationResponseDto> getMyRegistrations(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        List<CourseRegistration> registrations = courseRegistrationRepository.findByStudent(student);

        return registrations.stream()
                .map(reg -> CourseRegistrationResponseDto.builder()
                        .registrationId(reg.getId())
                        .lectureId(reg.getLecture().getLectureId())
                        .lectureName(reg.getLecture().getSubject().getSubjectName())
                        .subjectName(reg.getLecture().getSubject().getSubjectName())
                        .teacherName(reg.getLecture().getTeacher().getName())
                        .dayOfWeek(reg.getLecture().getDayOfWeek().name())
                        .period(reg.getLecture().getPeriod())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseRegistrationResponseDto getRegistrationDetail(String email, Long registrationId) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        CourseRegistration reg = courseRegistrationRepository.findByIdAndStudent_Id(registrationId, student.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.REGISTRATION_NOT_FOUND));

        return CourseRegistrationResponseDto.builder()
                .registrationId(reg.getId())
                .lectureId(reg.getLecture().getLectureId())
                .lectureName(reg.getLecture().getSubject().getSubjectName())
                .subjectName(reg.getLecture().getSubject().getSubjectName())
                .teacherName(reg.getLecture().getTeacher().getName())
                .dayOfWeek(reg.getLecture().getDayOfWeek().name())
                .period(reg.getLecture().getPeriod())
                .build();
    }

    @Transactional
    public void cancelRegistration(String email, Long registrationId) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        CourseRegistration reg = courseRegistrationRepository.findByIdAndStudent_Id(registrationId, student.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.REGISTRATION_NOT_FOUND));

        courseRegistrationRepository.delete(reg);
    }
}
