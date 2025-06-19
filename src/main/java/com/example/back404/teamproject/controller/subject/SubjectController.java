package com.example.back404.teamproject.controller.subject;

import com.example.back404.teamproject.common.constants.ApiMappingPattern;
import com.example.back404.teamproject.common.constants.enums.Affiliation;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectListGetResponseDto;
import com.example.back404.teamproject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.SUBJECT_API)
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    // 과목 목록 검색 조회 (반환 LIST) - 교사 / 관리자
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<SubjectListGetResponseDto>>> searchSubjects(
            @AuthenticationPrincipal String userId,
            // 검색 데이터 전부를 RequestParam으로 나열
            @RequestParam(required = false) String subjectId, String subjectName, String grade, String semester, Affiliation affiliation
    ) {
        ResponseDto<List<SubjectListGetResponseDto>> results = subjectService.getSubjects(userId, subjectId, subjectName, grade, semester, affiliation);
        return ResponseEntity.ok(results);
    }

    // 과목 상세 조회 (반환 단건) - 교사 / 관리자
    @GetMapping("/{subjectId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<SubjectGetResponseDto>> getSharedSubjectById(
            @AuthenticationPrincipal String userId,
            @PathVariable String subjectId
    ) {
        ResponseDto<SubjectGetResponseDto> result = subjectService.getSubjectById(userId, subjectId);
        return ResponseEntity.ok(result);
    }

    //# TeacherSubjectController에서 구현 (선우님 담당)
    // 과목 수정 - 교사 (프론트엔드 파트 담당)
}
