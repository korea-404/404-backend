package com.example.back404.teamproject.controller.subject;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
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

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<List<SubjectListGetResponseDto>>> searchSubjects(
            @AuthenticationPrincipal String email,
            @RequestParam(required = false) String subjectName,
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) String semester,
            @RequestParam(required = false) SubjectAffiliation subjectAffiliation
    ) {
        ResponseDto<List<SubjectListGetResponseDto>> results = subjectService.searchSubjects(
                email, subjectName, grade, semester, subjectAffiliation);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{subjectId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ResponseDto<SubjectGetResponseDto>> getSharedSubjectById(
            @AuthenticationPrincipal String userId,
            @PathVariable String subjectId
    ) {
        ResponseDto<SubjectGetResponseDto> result = subjectService.getSubjectById(userId, subjectId);
        return ResponseEntity.ok(result);
    }
}
