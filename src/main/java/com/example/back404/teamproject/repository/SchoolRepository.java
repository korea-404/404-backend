package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findBySchoolCode(Integer schoolCode);
    Optional<School> findBySchoolAdminName(String name);
    Optional<School> findBySchoolAdminEmail(String email);
    boolean existsBySchoolCode(Integer schoolCode);
}
