package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.common.enums.CourseRegistrationStatus;
import com.example.back404.teamproject.entity.CourseRegistration;
import com.example.back404.teamproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {

    // 시간표 조회용
    List<CourseRegistration> findByStudentAndStatus(Student student, CourseRegistrationStatus status);

    // 중복 수강신청 확인
    boolean existsByStudent_IdAndLecture_Id(String studentId, Long lectureId);

    // 수강인원 확인 (전체 카운트)
    int countByLecture_Id(Long lectureId);

    // 특정 학생의 전체 수강신청 목록 조회
    List<CourseRegistration> findByStudent_Id(String studentId);

    // 수강신청 상세 조회 (학생 본인 여부 확인 포함)
    Optional<CourseRegistration> findByIdAndStudent_Id(Long registrationId, String studentId);

    // 특정 강의에 대한 확정 수강 인원만 카운트
    int countByLecture_IdAndStatus(Long lectureId, CourseRegistrationStatus status);

    // 특정 학생 + 강의 조합 직접 조회
    Optional<CourseRegistration> findByStudent_IdAndLecture_Id(String studentId, Long lectureId);
}
