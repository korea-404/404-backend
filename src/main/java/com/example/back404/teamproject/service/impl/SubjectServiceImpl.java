package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseMessage;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.dto.common.ResponseDto;
import com.example.back404.teamproject.dto.subject.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subject.response.SubjectListGetResponseDto;
import com.example.back404.teamproject.entity.Subject;
import com.example.back404.teamproject.repository.*;
import com.example.back404.teamproject.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final LectureRepository lectureRepository;


//    @Override
//    public ResponseDto<List<SubjectListGetResponseDto>> searchSubjects(String email, String subjectName, String grade, String semester, Affiliation affiliation) {
//
//        List<SubjectListGetResponseDto> datas = null;
//
//        // userId (사용자 정보)가 회원인지 여부 확인 + 권한이 TEACHER || ADMIN 인지 확인
//        // : 회원이 아닐 경우 OR 권한자가 아니면 반환 (예외 발생)
//
////        User user = userRepository.findByEmail(email)
////                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_USER + ": " + email));
//
////        String userRole = user.getRole();
//

    @Override
    public ResponseDto<List<SubjectListGetResponseDto>> searchSubjects(String userId, String subjectName, String grade, String semester, SubjectAffiliation affiliation) {
        return null;
    }

    ////        if (!("ROLE_ADMIN".equals(userRole) || "ROLE_TEACHER".equals(userRole))) {
////            throw new AccessDeniedException("이 기능에 접근할 권한이 없습니다.");
////        }
//
//        // 과목명 - 비워져 있는지 확인 (조건문 if/else문)
//        // 1) 비워진 경우 select-option 으로만 검색
//        // 2) 존재하는 경우 input + select-option 으로 검색
//        // 3) input + select-option 둘 다 선택하지 않고 검색한 경우 예외 발생
//
//        List<Subject> FilteredSubjects;
//
//
//        // 반환 값을 List<SubjectListGetResponseDto로 변환
//        List<SubjectListGetResponseDto> datas = FilteredSubjects.stream()
//                .map(subject -> SubjectListGetResponseDto.builder()
//                        .subjectName(subject.getSubjectName())
//                        .grade(subject.getGrade())
//                        .semester(subject.getSemester())
//                        .affiliation(subject.getAffiliation())
//                        .build())
//                .collect(Collectors.toList());
//
//        return ResponseDto.setSuccess(ResponseMessage.GET_SUBJECT_LIST_SUCCESS, datas);


    @Override
    public ResponseDto<SubjectGetResponseDto> getSubjectById(String userId, String subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT + ": " + subjectId));

//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_USER + ": " + userId));

        SubjectGetResponseDto responseData = SubjectGetResponseDto.builder()
                .subjectId(subject.getSubjectId())
                .schoolId(subject.getSchool().getSchoolId())
                .subjectName(subject.getSubjectName())
                .grade(subject.getGrade())
                .semester(subject.getSemester())
                .affiliation(subject.getAffiliation())
                .status(subject.getStatus())
                .maxEnrollment(subject.getMaxEnrollment())
                .build();
        return ResponseDto.setSuccess(ResponseMessage.GET_SUBJECT_DETAIL_SUCCESS, responseData);
    }

}
