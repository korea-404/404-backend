package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Student> findByUsername(String username);

    Optional<Student> findByEmail(String email);

}
