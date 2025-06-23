package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {

    // 검색 & 필터링
    List<Subject> findBySubjectName(String subjectName);

    List<Subject> findByGrade(String grade);

    List<Subject> findBySemester(String semester);

    List<Subject> findByAffiliation(SubjectAffiliation affiliation);
}