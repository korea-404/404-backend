package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.ResponseMessage;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.dto.subject.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subject.response.SubjectListGetResponseDto;
import com.example.back404.teamproject.entity.Subject;
import com.example.back404.teamproject.repository.SubjectRepository;
import com.example.back404.teamproject.repository.LectureRepository;
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

    @Override
    public ResponseDto<List<SubjectListGetResponseDto>> searchSubjects(String userId, String subjectName, String grade, String semester, SubjectAffiliation affiliation) {
        try {
            List<Subject> subjects = subjectRepository.findAll();

            if (subjectName != null && !subjectName.isEmpty()) {
                subjects = subjects.stream()
                        .filter(s -> s.getSubjectName().contains(subjectName))
                        .toList();
            }

            if (grade != null && !grade.isEmpty()) {
                subjects = subjects.stream()
                        .filter(s -> s.getGrade().equals(grade))
                        .toList();
            }

            if (semester != null && !semester.isEmpty()) {
                subjects = subjects.stream()
                        .filter(s -> s.getSemester().equals(semester))
                        .toList();
            }

            if (affiliation != null) {
                subjects = subjects.stream()
                        .filter(s -> s.getAffiliation().equals(affiliation))
                        .toList();
            }

            List<SubjectListGetResponseDto> responseList = subjects.stream()
                    .map(subject -> SubjectListGetResponseDto.builder()
                            .subjectName(subject.getSubjectName())
                            .grade(subject.getGrade())
                            .semester(subject.getSemester())
                            .affiliation(subject.getAffiliation())
                            .build())
                    .toList();

            return ResponseDto.setSuccess(ResponseMessage.GET_SUBJECT_LIST_SUCCESS, responseList);
        } catch (Exception e) {
            return ResponseDto.setFailed("과목 목록 조회 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseDto<SubjectGetResponseDto> getSubjectById(String userId, String subjectId) {
        try {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT + ": " + subjectId));

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
        } catch (EntityNotFoundException e) {
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            return ResponseDto.setFailed("과목 상세 정보 조회 중 오류가 발생했습니다.");
        }
    }
}
