package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.common.constants.enums.SubjectAffiliation;
import com.example.back404.teamproject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    List<Subject> findByAffiliation(SubjectAffiliation affiliation);
}
