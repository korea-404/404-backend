package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findBySubjectId_SubjectNameContaining(String subjectName);

    boolean existsBySubjectId(Subject subject);
}