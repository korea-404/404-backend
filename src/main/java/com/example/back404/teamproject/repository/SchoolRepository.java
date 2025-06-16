package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    boolean existsBySchoolCode(Integer schoolCode);

    boolean existsBySchoolAdminEmail(String email);

    Optional<School> findBySchoolAdminEmail(String email);

    Optional<School> findBySchoolCodeAndDeletedAtIsNull(Integer schoolCode);

    Optional<School> findBySchoolAdminEmailAndIsEmailVerifiedTrue(String email);

    Optional<School> findBySchoolCode(Integer schoolCode);

    Optional<School> findBySchoolAdminNameAndIsEmailVerifiedTrue(String schoolAdminName);

}
