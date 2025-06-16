package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.constants.ResponseDto;
import com.example.back404.teamproject.common.constants.ResponseMessage;
import com.example.back404.teamproject.common.constants.enums.SubjectAffiliation;
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
    public ResponseDto<List<SubjectListDto>> getAllSubjects(SubjectAffiliation affiliation) {
       List<Subject> subjects;

        if (affiliation == null) {
            subjects = subjectRepository.findAll();
        } else {
            subjects = subjectRepository.findByAffiliation(affiliation);
        }
        List<SubjectListDto> dto = subjects.stream()
                .map(subject -> SubjectListDto.builder()
                        .subjectId(subject.getSubjectId())
                        .subjectName(subject.getSubjectName())
                        .grade(subject.getGrade())
                        .semester(subject.getSemester())
                        .affiliation(subject.getAffiliation())
                        .build())
                .collect(Collectors.toList());
        return ResponseDto.setSuccess("과목 전체 목록 조회 성공", dto);
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

            return ResponseDto.setSuccess("과목 상세 조회 성공", responseData);
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
                    .subjectName(subject.getSubjectName())
                    .grade(subject.getGrade())
                    .semester(subject.getSemester())
                    .affiliation(subject.getAffiliation())
                    .status(subject.getStatus())
                    .maxEnrollment(subject.getMaxEnrollment())
                    .build();

            return ResponseDto.setSuccess("과목 상태가 성공적으로 변경되었습니다.", responseData);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }


    // 승인 거절
    @Override
    public ResponseDto<SubjectDetailDto> rejectSubject(String subjectId) {
        try {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException("과목을 등록 할 수 없습니다. " + subjectId));

            if (subject.getStatus() != SubjectStatus.pending) {
                throw new IllegalStateException("'승인 대기' 상태의 과목만 거절할 수 있습니다.");
            }
            subject.setStatus(SubjectStatus.rejected);

            SubjectDetailDto responseData = SubjectDetailDto.builder()
                    .subjectId(subject.getSubjectId())
                    .subjectName(subject.getSubjectName())
                    .grade(subject.getGrade())
                    .semester(subject.getSemester())
                    .affiliation(subject.getAffiliation())
                    .status(subject.getStatus())
                    .maxEnrollment(subject.getMaxEnrollment())
                    .build();

            return ResponseDto.setSuccess("과목이 거절 처리되었습니다.", responseData);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }


    // 과목 승인 -> 강의 자동 생성
    @Override
    public ResponseDto<LectureResponseDto> approveSubjectAndCreateLecture(String subjectId, SubjectApprovalRequestDto requestDto) {
        try {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException("과목을 등록 할 수 없습니다. " + subjectId));

            Teacher teacher = teacherRepository.findById(requestDto.getTeacherId())
                    .orElseThrow(() -> new EntityNotFoundException("선생님을 찾을 수 없습니다. ID: " + requestDto.getTeacherId()));

            if (subject.getStatus() != SubjectStatus.pending) {
                throw new IllegalStateException("'승인 대기' 상태의 과목만 처리할 수 있습니다.");
            }
            subject.setStatus(SubjectStatus.approved);

            Lecture lecture = Lecture.builder()
                    .school(subject.getSchoolId())
                    .subject(subject)
                    .teacher(teacher)
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

            return ResponseDto.setSuccess("강의가 성공적으로 승인 및 생성되었습니다.", responseData);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }
}