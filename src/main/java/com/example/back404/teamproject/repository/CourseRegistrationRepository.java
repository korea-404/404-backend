package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.common.enums.CourseRegistrationStatus;
import com.example.back404.teamproject.entity.CourseRegistration;
import com.example.back404.teamproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {

    boolean existsByStudent_IdAndLecture_LectureId(Long studentId, Long lectureId);

    List<CourseRegistration> findByStudent(Student student);

    Optional<CourseRegistration> findByIdAndStudent_Id(Long registrationId, Long studentId);

    List<CourseRegistration> findByStudentAndStatus(Student student, CourseRegistrationStatus courseRegistrationStatus);
}
