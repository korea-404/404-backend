package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.common.constants.ApiMappingPattern;
import com.example.back404.teamproject.common.constants.ResponseDto;
import com.example.back404.teamproject.common.constants.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.constants.enums.SubjectStatus;
import com.example.back404.teamproject.dto.lectures.response.LectureResponseDto;
import com.example.back404.teamproject.dto.subjects.request.SubjectApprovalRequestDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectDetailDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectListDto;
import com.example.back404.teamproject.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.SUBJECT_API)
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    // 과목 전체 목록 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<SubjectListDto>>> getSubjectList() {
        return ResponseEntity.ok(subjectService.getAllSubjects(SubjectAffiliation affiliation));
    }

    // 과목 상세 정보 조회
    @GetMapping("/{subjectId}")
    public ResponseEntity<ResponseDto<SubjectDetailDto>> getSubjectDetail(@PathVariable String subjectId) {
        return ResponseEntity.ok(subjectService.getSubjectById(subjectId));
    }

    // 등록 과목을 '대기/승인/거절' 상태로 변경
    @PutMapping("/{subjectId}/status/{newStatus}")
    public ResponseEntity<ResponseDto<SubjectDetailDto>> updateStatus(@PathVariable String subjectId, @PathVariable SubjectStatus newStatus) {
        return ResponseEntity.ok(subjectService.updateSubjectStatus(subjectId, newStatus));
    }

    // 등록 과목을 승인 거절
    @PostMapping("/{subjectId}/rejection")
    public ResponseEntity<ResponseDto<SubjectDetailDto>> rejectSubject(@PathVariable String subjectId) {
        return ResponseEntity.ok(subjectService.rejectSubject(subjectId));
    }

    // 과목 승인 및 강의 자동 생성
    @PostMapping("/{subjectId}/approval")
    public ResponseEntity<ResponseDto<LectureResponseDto>> approveSubject(@PathVariable String subjectId, @Valid @RequestBody SubjectApprovalRequestDto requestDto) {
        ResponseDto<LectureResponseDto> response = subjectService.approveSubjectAndCreateLecture(subjectId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}