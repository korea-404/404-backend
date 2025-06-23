package com.example.back404.teamproject.controller.lecture;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.dto.common.ResponseDto;
import com.example.back404.teamproject.dto.lecture.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lecture.response.LectureDetailResponseDto;
import com.example.back404.teamproject.dto.lecture.response.LectureListDto;
import com.example.back404.teamproject.dto.lecture.response.LectureSimpleResponseDto;
import com.example.back404.teamproject.service.impl.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.LECTURE_API)
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    // 전체 강의 목록 조회
    @GetMapping
    public ResponseEntity<List<LectureSimpleResponseDto>> getLectureList() {
        return ResponseEntity.ok(lectureService.getAllLectures());
    }

    // 강의 상세 조회
    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureDetailResponseDto> getLectureDetail(@PathVariable Long lectureId) {
        return ResponseEntity.ok(lectureService.getLectureDetail(lectureId));
    }

    // 강의 수정
    @PutMapping("/{lectureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<LectureListDto>> updateLecture(
            @PathVariable Long lectureId,
            @Valid @RequestBody LectureUpdateRequestDto requestDto) {

        return ResponseEntity.ok(lectureService.updateLecture(lectureId, requestDto));
    }

    // 강의 삭제
    @DeleteMapping("/{lectureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto<?>> deleteLecture(@PathVariable Long lectureId) {

        return ResponseEntity.ok(lectureService.deleteLecture(lectureId));
    }
}
