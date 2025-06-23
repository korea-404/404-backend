package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.enums.ErrorCode;
import com.example.back404.teamproject.common.exception.CustomException;
import com.example.back404.teamproject.dto.registration.request.CourseRegistrationRequestDto;
import com.example.back404.teamproject.dto.registration.response.CourseRegistrationResponseDto;
import com.example.back404.teamproject.entity.CourseRegistration;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.repository.CourseRegistrationRepository;
import com.example.back404.teamproject.repository.LectureRepository;
import com.example.back404.teamproject.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseRegistrationService {

    private final CourseRegistrationRepository courseRegistrationRepository;
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    // 학생 ID로 수강신청
    public void register(String studentId, CourseRegistrationRequestDto request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        Lecture lecture = lectureRepository.findById(request.getLectureId())
                .orElseThrow(() -> new CustomException(ErrorCode.LECTURE_NOT_FOUND));

        if (courseRegistrationRepository.existsByStudent_IdAndLecture_Id(studentId, request.getLectureId())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED);
        }

        int count = courseRegistrationRepository.countByLecture_Id(request.getLectureId());
        if (count >= lecture.getMaxStudents()) {
            throw new CustomException(ErrorCode.LECTURE_FULL);
        }

        CourseRegistration registration = CourseRegistration.builder()
                .student(student)
                .lecture(lecture)
                .build();

        courseRegistrationRepository.save(registration);
    }

    // 이메일로 수강신청
    public void registerByEmail(String email, CourseRegistrationRequestDto request) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        Lecture lecture = lectureRepository.findById(request.getLectureId())
                .orElseThrow(() -> new CustomException(ErrorCode.LECTURE_NOT_FOUND));

        if (courseRegistrationRepository.existsByStudent_IdAndLecture_Id(student.getId(), lecture.getId())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED);
        }

        int count = courseRegistrationRepository.countByLecture_Id(lecture.getId());
        if (count >= lecture.getMaxStudents()) {
            throw new CustomException(ErrorCode.LECTURE_FULL);
        }

        CourseRegistration registration = CourseRegistration.builder()
                .student(student)
                .lecture(lecture)
                .build();

        courseRegistrationRepository.save(registration);
    }

    public List<CourseRegistrationResponseDto> getMyRegistrations(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        List<CourseRegistration> registrations = courseRegistrationRepository.findByStudent_Id(student.getId());

        return registrations.stream().map(reg -> {
            Lecture lec = reg.getLecture();
            return CourseRegistrationResponseDto.builder()
                    .registrationId(reg.getId())
                    .lectureId(lec.getId())
                    .lectureName(lec.getSubject().getName())
                    .subjectName(lec.getSubject().getName())
                    .teacherName(lec.getTeacher().getName())
                    .dayOfWeek(lec.getDayOfWeek())
                    .period(lec.getPeriod())
                    .build();
        }).toList();
    }

    public CourseRegistrationResponseDto getRegistrationDetail(String email, Long registrationId) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        CourseRegistration reg = courseRegistrationRepository.findByIdAndStudent_Id(registrationId, student.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.REGISTRATION_NOT_FOUND));

        Lecture lec = reg.getLecture();

        return CourseRegistrationResponseDto.builder()
                .registrationId(reg.getId())
                .lectureId(lec.getId())
                .lectureName(lec.getSubject().getName())
                .subjectName(lec.getSubject().getName())
                .teacherName(lec.getTeacher().getName())
                .dayOfWeek(lec.getDayOfWeek())
                .period(lec.getPeriod())
                .build();
    }

    public void cancelRegistration(String email, Long registrationId) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.STUDENT_NOT_FOUND));

        CourseRegistration reg = courseRegistrationRepository.findByIdAndStudent_Id(registrationId, student.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.REGISTRATION_NOT_FOUND));

        courseRegistrationRepository.delete(reg);
    }

}
