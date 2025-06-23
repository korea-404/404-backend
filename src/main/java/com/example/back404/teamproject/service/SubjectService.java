package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.constants.enums.Affiliation;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectListGetResponseDto;

import java.util.List;

public interface SubjectService {

    // 과목 목록 검색 조회 (반환 LIST) - 교사 / 관리자
    ResponseDto<List<SubjectListGetResponseDto>> searchSubjects(String userId, String subjectName, String grade, String semester, Affiliation affiliation);

    // 과목 상세 조회 (반환 단건) - 교사 / 관리자
    ResponseDto<SubjectGetResponseDto> getSubjectById(String userId, String subjectId);

    // 과목 상태 변경 (승인/거절/수정/삭제) - 관리자

    // 과목 수정 - 교사 (프론트엔드 파트 담당)
}