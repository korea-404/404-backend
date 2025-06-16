package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.constants.ResponseDto;
import com.example.back404.teamproject.common.constants.ResponseMessage;
import com.example.back404.teamproject.common.constants.enums.SubjectStatus;
import com.example.back404.teamproject.dto.lectures.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lectures.response.LectureResponseDto;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.Subject;
import com.example.back404.teamproject.entity.Teacher;
import com.example.back404.teamproject.repository.LectureRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.LectureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public ResponseDto<LectureResponseDto> updateLecture(Long lectureId, LectureUpdateRequestDto requestDto) {
        try {
            Lecture lecture = lectureRepository.findById(lectureId)
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_LECTURE));
            Teacher teacher = teacherRepository.findById(requestDto.getTeacherId())
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_TEACHER));

            lecture.setTeacherId(teacher);
            lecture.setDayOfWeek(requestDto.getDayOfWeek());
            lecture.setPeriod(requestDto.getPeriod());
            lecture.setMaxEnrollment(requestDto.getMaxEnrollment());

            LectureResponseDto responseData = LectureResponseDto.builder()
                    .lectureId(lecture.getLectureId())
                    .subjectName(lecture.getSubjectId().getSubjectName())
                    .teacherName(lecture.getTeacherId().getTeacherName())
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
            subject.setStatus(SubjectStatus.pending);
            lectureRepository.delete(lecture);

            return ResponseDto.setSuccess(ResponseMessage.DELETE_LECTURE_SUCCESS, null);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }
}