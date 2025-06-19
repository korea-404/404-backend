package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.common.constants.ApiMappingPattern;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.lectures.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lectures.response.LectureDetailDto;
import com.example.back404.teamproject.dto.lectures.response.LectureListDto;
import com.example.back404.teamproject.dto.lectures.response.LectureResponseDto;
import com.example.back404.teamproject.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    // 강의 수정
    @PutMapping("/admin/lectures/{lectureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<LectureResponseDto>> updateLecture(
            @PathVariable Long lectureId,
            @Valid @RequestBody LectureUpdateRequestDto requestDto) {

        return ResponseEntity.ok(lectureService.updateLecture(lectureId, requestDto));
    }

    // 강의 조회
    @GetMapping("/admin/lectures")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<List<LectureListDto>>> getAllLecturesAdmin() {
        return ResponseEntity.ok(lectureService.getAllLecturesAdmin());
    }

    // 강의 삭제
    @DeleteMapping("/admin/lectures/{lectureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<?>> deleteLecture(@PathVariable Long lectureId) {

        return ResponseEntity.ok(lectureService.deleteLecture(lectureId));
    }

    // 강의 목록 조회 (과목명 검색)
    @GetMapping("/lectures")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<LectureListDto>>> getLectureList(
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(lectureService.getLectureList(name));
   }

    //  강의 상세 정보 조회
    @GetMapping("/lectures/{lectureId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<LectureDetailDto>> getLectureDetail(
            @PathVariable Long lectureId) {
        return ResponseEntity.ok(lectureService.getLectureDetail(lectureId));
    }
}
