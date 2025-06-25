package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Query("SELECT l FROM Lecture l " +
            "JOIN FETCH l.subject s " +
            "JOIN FETCH l.teacher t " +
            "JOIN FETCH l.classroom c")
    List<Lecture> findAllWithSubjectAndTeacher();

    @Query("SELECT l FROM Lecture l " +
            "JOIN FETCH l.subject s " +
            "JOIN FETCH l.teacher t " +
            "JOIN FETCH l.classroom c " +
            "WHERE l.lectureId = :lectureId")
    Optional<Lecture> findByIdWithSubjectTeacherClassroom(Long lectureId);
}
