package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.constants.ResponseMessage;
import com.example.back404.teamproject.common.constants.enums.SubjectStatus;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.lectures.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lectures.response.LectureDetailDto;
import com.example.back404.teamproject.dto.lectures.response.LectureListDto;
import com.example.back404.teamproject.dto.lectures.response.LectureResponseDto;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Subject;
import com.example.back404.teamproject.entity.Teacher;
import com.example.back404.teamproject.repository.LectureRepository;
import com.example.back404.teamproject.repository.SubjectRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.LectureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<LectureListDto>> getAllLecturesAdmin() {
        List<Lecture> lectures = lectureRepository.findAll();

        List<LectureListDto> dto = lectures.stream()
                .map(lecture -> LectureListDto.builder()
                        .lectureId(lecture.getLectureId())
                        .subjectName(lecture.getSubjectId().getSubjectName())
                        .teacherName(lecture.getTeacherId().getName())
                        .dayOfWeek(lecture.getDayOfWeek())
                        .period(lecture.getPeriod())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.GET_LECTURE_LIST_SUCCESS, dto);
    }

    @Override
    public ResponseDto<LectureResponseDto> updateLecture(Long lectureId, LectureUpdateRequestDto requestDto) {
        try {
            Lecture lecture = lectureRepository.findById(lectureId)
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_LECTURE));
            Teacher teacher = teacherRepository.findById(requestDto.getTeacherId())
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_TEACHER));

            lecture.updateInfo(teacher, requestDto);

            LectureResponseDto responseData = LectureResponseDto.builder()
                    .lectureId(lecture.getLectureId())
                    .subjectName(lecture.getSubjectId().getSubjectName())
                    .teacherName(lecture.getTeacherId().getName())
                    .dayOfWeek(lecture.getDayOfWeek())
                    .period(lecture.getPeriod())
                    .allowedGrade(lecture.getAllowedGrade())
                    .build();

            return ResponseDto.setSuccess(ResponseMessage.UPDATE_LECTURE_SUCCESS, responseData);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }

    @Override
    public ResponseDto<?> deleteLecture(Long lectureId) {
        try {
            Lecture lecture = lectureRepository.findById(lectureId)
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_LECTURE));

            Subject subject = lecture.getSubjectId();
            subject.updateStatus(SubjectStatus.PENDING);
            subjectRepository.save(subject);
            lectureRepository.delete(lecture);

            return ResponseDto.setSuccess(ResponseMessage.DELETE_LECTURE_SUCCESS, null);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }
    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<LectureListDto>> getLectureList(String name) {
        List<Lecture> lectures;
        if (name == null || name.isBlank()) {
            lectures = lectureRepository.findAll();
        } else {
            lectures = lectureRepository.findBySubjectId_SubjectNameContaining(name);
        }

        List<LectureListDto> dto = lectures.stream()
                .map(lecture -> LectureListDto.builder()
                        .lectureId(lecture.getLectureId())
                        .subjectName(lecture.getSubjectId().getSubjectName())
                        .teacherName(lecture.getTeacherId().getName())
                        .dayOfWeek(lecture.getDayOfWeek())
                        .period(lecture.getPeriod())
                        .build())
                .collect(Collectors.toList());
        return ResponseDto.setSuccess(ResponseMessage.GET_LECTURE_LIST_SUCCESS, dto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<LectureDetailDto> getLectureDetail(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_LECTURE + ": " + lectureId));

        LectureDetailDto dto = LectureDetailDto.builder()
                .lectureId(lecture.getLectureId())
                .subjectName(lecture.getSubjectId().getSubjectName())
                .teacherName(lecture.getTeacherId().getName())
                .dayOfWeek(lecture.getDayOfWeek())
                .period(lecture.getPeriod())
                .grade(lecture.getAllowedGrade())
                .maxEnrollment(lecture.getMaxEnrollment())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.GET_LECTURE_DETAIL_SUCCESS, dto);
    }
}