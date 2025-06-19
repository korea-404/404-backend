package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.constants.ResponseMessage;
import com.example.back404.teamproject.common.constants.enums.Affiliation;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subjects.response.SubjectListGetResponseDto;
import com.example.back404.teamproject.entity.Subject;
import com.example.back404.teamproject.repository.LectureRepository;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.repository.SubjectRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
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
    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public ResponseDto<List<SubjectListGetResponseDto>> getSubjects(String userId, String subjectId, String subjectName, String grade, String semester, Affiliation affiliation) {

        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseDto<SubjectGetResponseDto> getSubjectById(String userId, String subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT + ": " + subjectId));


        SubjectGetResponseDto responseData = SubjectGetResponseDto.builder()
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
    }

//    @Override
//    @Transactional(readOnly = true)
//    public ResponseDto<List<SubjectListGetResponseDto>>  getAllSubjectsAdmin() {
//        List<Subject> subjects = subjectRepository.findAll();
//
//        List<SubjectListGetResponseDto> dto = subjects.stream()
//                .map(subject -> SubjectListGetResponseDto.builder()
//                        .subjectId(subject.getSubjectId())
//                        .subjectName(subject.getSubjectName())
//                        .grade(subject.getGrade())
//                        .semester(subject.getSemester())
//                        .affiliation(subject.getAffiliation())
//                        .build())
//                .collect(Collectors.toList());
//        return ResponseDto.setSuccess(ResponseMessage.GET_SUBJECT_LIST_SUCCESS, dto);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public ResponseDto<List<SubjectListGetResponseDto>> getSubjects(String name) {
//        List<Subject> subjects;
//        if (name == null || name.isBlank()) {
//            subjects = subjectRepository.findAll();
//        }
//        else {
//            subjects = subjectRepository.findBySubjectNameContaining(name);
//        }
//        List<SubjectListGetResponseDto> dto = subjects.stream()
//                .map(subject -> SubjectListGetResponseDto.builder()
//                        .subjectId(subject.getSubjectId())
//                        .subjectName(subject.getSubjectName())
//                        .grade(subject.getGrade())
//                        .semester(subject.getSemester())
//                        .affiliation(subject.getAffiliation())
//                        .build())
//                .collect(Collectors.toList());
//        return ResponseDto.setSuccess(ResponseMessage.GET_SUBJECT_LIST_SUCCESS, dto);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public ResponseDto<SubjectGetResponseDto> getSubjectById(String subjectId) {
//        Subject subject = subjectRepository.findById(subjectId)
//                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT + ": " + subjectId));
//        SubjectGetResponseDto responseData = SubjectGetResponseDto.builder()
//                .subjectId(subject.getSubjectId())
//                .schoolId(subject.getSchoolId().getSchoolId())
//                .subjectName(subject.getSubjectName())
//                .grade(subject.getGrade())
//                .semester(subject.getSemester())
//                .affiliation(subject.getAffiliation())
//                .status(subject.getStatus())
//                .maxEnrollment(subject.getMaxEnrollment())
//                .build();
//        return ResponseDto.setSuccess(ResponseMessage.GET_SUBJECT_DETAIL_SUCCESS, responseData);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public ResponseDto<SubjectGetResponseDto> getSharedSubjectById(String subjectId) {
//        Subject subject = subjectRepository.findById(subjectId)
//                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT + ": " + subjectId));
//
//        SubjectGetResponseDto responseData = SubjectGetResponseDto.builder()
//                .subjectId(subject.getSubjectId())
//                .schoolId(subject.getSchoolId().getSchoolId())
//                .subjectName(subject.getSubjectName())
//                .grade(subject.getGrade())
//                .semester(subject.getSemester())
//                .affiliation(subject.getAffiliation())
//                .status(subject.getStatus())
//                .maxEnrollment(subject.getMaxEnrollment())
//                .build();
//        return ResponseDto.setSuccess(ResponseMessage.GET_SUBJECT_DETAIL_SUCCESS, responseData);
//    }
//
//    @Override
//    public ResponseDto<SubjectGetResponseDto> updateSubjectStatus(String subjectId, SubjectStatus newStatus) {
//        try {
//            Subject subject = subjectRepository.findById(subjectId)
//                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT));
//            subject.updateStatus(newStatus);
//            SubjectGetResponseDto responseData = SubjectGetResponseDto.builder()
//                    .subjectId(subject.getSubjectId())
//                    .schoolId(subject.getSchoolId().getSchoolId())
//                    .subjectName(subject.getSubjectName())
//                    .grade(subject.getGrade())
//                    .semester(subject.getSemester())
//                    .affiliation(subject.getAffiliation())
//                    .status(subject.getStatus())
//                    .maxEnrollment(subject.getMaxEnrollment())
//                    .build();
//            return ResponseDto.setSuccess(ResponseMessage.UPDATE_SUBJECT_STATUS_SUCCESS, responseData);
//        } catch (Exception e) {
//            return ResponseDto.setFailed(e.getMessage());
//        }
//    }
//
//    @Override
//    public ResponseDto<LectureResponseDto> approveSubjectAndCreateLecture(String subjectId, SubjectApprovalRequestDto requestDto) {
//        try {
//            Subject subject = subjectRepository.findById(subjectId)
//                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT));
//            if (subject.getStatus() != SubjectStatus.PENDING) {
//                throw new IllegalStateException(ResponseMessage.CANNOT_PROCESS_PENDING_ONLY);
//            }
//            subject.updateStatus(SubjectStatus.APPROVED);
//
//            Teacher teacher = teacherRepository.findById(requestDto.getTeacherId())
//                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_TEACHER));
//
//            Lecture lecture = Lecture.builder()
//                    .schoolId(subject.getSchoolId())
//                    .subjectId(subject)
//                    .teacherId(teacher)
//                    .dayOfWeek(requestDto.getDayOfWeek())
//                    .period(requestDto.getPeriod())
//                    .allowedGrade(requestDto.getAllowedGrade())
//                    .maxEnrollment(requestDto.getMaxEnrollment())
//                    .build();
//            lectureRepository.save(lecture);
//
//            LectureResponseDto responseData = LectureResponseDto.builder()
//                    .lectureId(lecture.getLectureId())
//                    .subjectName(lecture.getSubjectId().getSubjectName())
//                    .teacherName(lecture.getTeacherId().getName())
//                    .dayOfWeek(lecture.getDayOfWeek())
//                    .period(lecture.getPeriod())
//                    .allowedGrade(lecture.getAllowedGrade())
//                    .build();
//            return ResponseDto.setSuccess(ResponseMessage.APPROVE_SUBJECT_SUCCESS, responseData);
//        } catch (Exception e) {
//            return ResponseDto.setFailed(e.getMessage());
//        }
//    }
}