package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.constants.ResponseDto;
import com.example.back404.teamproject.common.constants.ResponseMessage;
import com.example.back404.teamproject.common.constants.enums.Affiliation;
import com.example.back404.teamproject.common.constants.enums.SubjectStatus;
import com.example.back404.teamproject.dto.lectures.response.LectureResponseDto;
import com.example.back404.teamproject.dto.subjects.request.SubjectApprovalRequestDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectDetailDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectListDto;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Subject;
import com.example.back404.teamproject.entity.Teacher;
import com.example.back404.teamproject.repository.LectureRepository;
import com.example.back404.teamproject.repository.SubjectRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    // 과목 전체 목록 조회
    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<SubjectListDto>> getAllSubjects(Affiliation affiliation) {
        List<Subject> subjects = (affiliation == null) ?
                subjectRepository.findAll() :
                subjectRepository.findByAffiliation(affiliation);

        List<SubjectListDto> dto = subjects.stream()
                .map(subject -> SubjectListDto.builder()
                        .subjectId(subject.getSubjectId())
                        .subjectName(subject.getSubjectName())
                        .grade(subject.getGrade())
                        .semester(subject.getSemester())
                        .affiliation(subject.getAffiliation())
                        .build())
                .collect(Collectors.toList());
        return ResponseDto.setSuccess(ResponseMessage.GET_SUBJECT_LIST_SUCCESS, dto);
    }

    // 과목 상세 정보 조회
    @Override
    @Transactional(readOnly = true)
    public ResponseDto<SubjectDetailDto> getSubjectById(String subjectId) {
        try {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT));

            SubjectDetailDto responseData = SubjectDetailDto.builder()
                    .subjectId(subject.getSubjectId())
                    .schoolId(subject.getSchoolId().getSchoolId())
                    .subjectName(subject.getSubjectName())
                    .grade(subject.getGrade())
                    .semester(subject.getSemester())
                    .affiliation(subject.getAffiliation())
                    .status(subject.getStatus())
                    .maxEnrollment(subject.getMaxEnrollment())
                    .build();
            return ResponseDto.setSuccess(ResponseMessage.GET_SUBJECT_DETAIL_SUCCESS, responseData);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }


    // 등록 과목을 '대기/승인/거절' 상태로 변경
    @Override
    public ResponseDto<SubjectDetailDto> updateSubjectStatus(String subjectId, SubjectStatus newStatus) {
        try {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException("과목을 등록 할 수 없습니다. " + subjectId));

            subject.setStatus(newStatus);

            SubjectDetailDto responseData = SubjectDetailDto.builder()
                    .subjectId(subject.getSubjectId())
                    .schoolId(subject.getSchoolId())
                    .subjectName(subject.getSubjectName())
                    .grade(subject.getGrade())
                    .semester(subject.getSemester())
                    .affiliation(subject.getAffiliation())
                    .status(subject.getStatus())
                    .maxEnrollment(subject.getMaxEnrollment())
                    .build();
            return ResponseDto.setSuccess(ResponseMessage.UPDATE_SUBJECT_STATUS_SUCCESS, responseData);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }


    // 승인 거절
    @Override
    public ResponseDto<SubjectDetailDto> rejectSubject(String subjectId) {
        try {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT));

            if (subject.getStatus() != SubjectStatus.pending) {
                throw new IllegalStateException(ResponseMessage.CANNOT_PROCESS_PENDING_ONLY);
            }

            subject.setStatus(SubjectStatus.rejected);

            SubjectDetailDto responseData = SubjectDetailDto.builder()
                    .subjectId(subject.getSubjectId())
                    .schoolId(subject.getSchoolId())
                    .subjectName(subject.getSubjectName())
                    .grade(subject.getGrade())
                    .semester(subject.getSemester())
                    .affiliation(subject.getAffiliation())
                    .status(subject.getStatus())
                    .maxEnrollment(subject.getMaxEnrollment())
                    .build();
            return ResponseDto.setSuccess(ResponseMessage.REJECT_SUBJECT_SUCCESS, responseData);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }


    // 과목 승인 -> 강의 자동 생성
    @Override
    public ResponseDto<LectureResponseDto> approveSubjectAndCreateLecture(String subjectId, SubjectApprovalRequestDto requestDto) {
        try {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT));
            if (subject.getStatus() != SubjectStatus.pending) {
                throw new IllegalStateException(ResponseMessage.CANNOT_PROCESS_PENDING_ONLY);
            }
            subject.setStatus(SubjectStatus.approved);

            Teacher teacher = teacherRepository.findById(requestDto.getTeacherId())
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_TEACHER));

            Lecture lecture = Lecture.builder()
                    .schoolId(subject.getSchoolId())
                    .subjectId(subject)
                    .teacherId(teacher)
                    .dayOfWeek(requestDto.getDayOfWeek())
                    .period(requestDto.getPeriod())
                    .allowedGrade(requestDto.getAllowedGrade())
                    .maxEnrollment(requestDto.getMaxEnrollment())
                    .build();
            lectureRepository.save(lecture);

            LectureResponseDto responseData = LectureResponseDto.builder()
                    .lectureId(lecture.getLectureId())
                    .subjectName(lecture.getSubjectId().getSubjectName())
                    .teacherName(lecture.getTeacherId().getName())
                    .dayOfWeek(lecture.getDayOfWeek())
                    .period(lecture.getPeriod())
                    .allowedGrade(lecture.getAllowedGrade())
                    .build();
            return ResponseDto.setSuccess(ResponseMessage.APPROVE_SUBJECT_SUCCESS, responseData);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }
}