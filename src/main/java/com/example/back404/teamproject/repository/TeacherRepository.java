package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Teacher> findByUsername(String username);
    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByNameAndEmail(String name, String email);
    Optional<Teacher> findByUsernameAndEmail(String username, String email);
}
