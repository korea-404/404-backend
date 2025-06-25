package com.example.back404.teamproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back404.teamproject.entity.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    List<Subject> findBySchoolIdOrderByCreatedAtDesc(Long schoolId);
    boolean existsById(String SubjectId);
}
