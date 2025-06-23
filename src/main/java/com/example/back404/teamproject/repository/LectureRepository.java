package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends CrudRepository<Lecture, Long> {

    @Query("SELECT l FROM Lecture l JOIN FETCH l.subject s JOIN FETCH l.teacher t")
    List<Lecture> findAllWithSubjectAndTeacher();

    @Query("SELECT l FROM Lecture l JOIN FETCH l.subject s JOIN FETCH l.teacher t JOIN FETCH l.classroom c WHERE l.lectureId = :lectureId")
    Optional<Lecture> findByIdWithSubjectTeacherClassroom(Long lectureId);

    List<Lecture> findBySubjectId_SubjectNameContaining(String subjectName);

    boolean existsBySubjectId(Subject subject);
}
