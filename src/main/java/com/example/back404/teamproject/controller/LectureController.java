package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.common.constants.ApiMappingPattern;
import com.example.back404.teamproject.common.constants.ResponseDto;
import com.example.back404.teamproject.dto.lectures.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lectures.response.LectureResponseDto;
import com.example.back404.teamproject.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.LECTURE_API)
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PutMapping("/{lectureId}")
    public ResponseEntity<ResponseDto<LectureResponseDto>> updateLecture(
            @PathVariable Long lectureId,
            @Valid @RequestBody LectureUpdateRequestDto requestDto) {

        return ResponseEntity.ok(lectureService.updateLecture(lectureId, requestDto));
    }

    @DeleteMapping("/{lectureId}")
    public ResponseEntity<ResponseDto<?>> deleteLecture(@PathVariable Long lectureId) {
        return ResponseEntity.ok(lectureService.deleteLecture(lectureId));
    }
}