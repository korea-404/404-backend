package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query("SELECT n FROM Notice n WHERE n.schoolId = :schoolId AND :currentDate BETWEEN n.startDate AND n.endDate ORDER BY n.createdAt DESC")
    List<Notice> findActiveNoticesBySchoolId(@Param("schoolId") Long schoolId, @Param("currentDate") LocalDate currentDate);

    List<Notice> findBySchoolIdOrderByCreatedAtDesc(Long schoolId);

}
