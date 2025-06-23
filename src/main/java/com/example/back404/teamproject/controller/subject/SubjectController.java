package com.example.back404.teamproject.controller.subject;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.dto.common.ResponseDto;
import com.example.back404.teamproject.dto.subject.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subject.response.SubjectListGetResponseDto;
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
            @AuthenticationPrincipal String email, // UserDetails 정보를 반환 (email, role 반환)
            @RequestParam(required = false) String subjectName, String grade, String semester, SubjectAffiliation subjectAffiliation
    ) {
        ResponseDto<List<SubjectListGetResponseDto>> results = subjectService.searchSubjects(email, subjectName, grade, semester, subjectAffiliation);
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
