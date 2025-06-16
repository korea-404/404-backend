package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.constants.ResponseDto;
import com.example.back404.teamproject.common.constants.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.constants.enums.SubjectStatus;
import com.example.back404.teamproject.dto.lectures.response.LectureResponseDto;
import com.example.back404.teamproject.dto.subjects.request.SubjectApprovalRequestDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectDetailDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectListDto;
import jakarta.validation.Valid;

import java.util.List;

public interface SubjectService {
    // 과목 전체 목록 조회
    ResponseDto<List<SubjectListDto>> getAllSubjects(SubjectAffiliation affiliation);
    // 과목 상세 정보 조회
    ResponseDto<SubjectDetailDto> getSubjectById( String subjectId);
    // 등록 과목을 '대기/승인/거절' 상태로 변경
    ResponseDto<SubjectDetailDto> updateSubjectStatus(String subjectId, SubjectStatus newStatus);
    // 승인 거절
    ResponseDto<SubjectDetailDto> rejectSubject(String subjectId);
    // 과목 승인 -> 강의 자동 생성
    ResponseDto<LectureResponseDto> approveSubjectAndCreateLecture(String subjectId, @Valid SubjectApprovalRequestDto dto);

}