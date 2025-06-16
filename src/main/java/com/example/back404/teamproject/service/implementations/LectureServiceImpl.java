package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.constants.ResponseDto;
import com.example.back404.teamproject.common.constants.enums.SubjectStatus;
import com.example.back404.teamproject.dto.lectures.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lectures.response.LectureResponseDto;
import com.example.back404.teamproject.entity.Lecture;
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
                    .orElseThrow(() -> new EntityNotFoundException("강의를 찾을 수 없습니다."));
            Teacher teacher = teacherRepository.findById(requestDto.getTeacherId())
                    .orElseThrow(() -> new EntityNotFoundException("선생님을 찾을 수 없습니다."));

            lecture.setTeacher(teacher);
            lecture.setDayOfWeek(requestDto.getDayOfWeek());
            lecture.setPeriod(requestDto.getPeriod());
            lecture.setMaxEnrollment(requestDto.getMaxEnrollment());

            LectureResponseDto responseData = LectureResponseDto.builder()
                    .lectureId(lecture.getLectureId())
                    .subjectName(lecture.getSubject().getSubjectName())
                    .teacherName(lecture.getTeacher().getTeacherName())
                    .dayOfWeek(lecture.getDayOfWeek())
                    .period(lecture.getPeriod())
                    .allowedGrade(lecture.getAllowedGrade())
                    .build();

            return ResponseDto.setSuccess("강의 정보가 성공적으로 수정되었습니다.", responseData);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }

    @Override
    public ResponseDto<?> deleteLecture(Long lectureId) {
        try {
            Lecture lecture = lectureRepository.findById(lectureId)
                    .orElseThrow(() -> new EntityNotFoundException("강의를 찾을 수 없습니다."));

            lecture.getSubject().updateStatus(SubjectStatus.pending);

            lectureRepository.delete(lecture);

            return ResponseDto.setSuccess("강의가 성공적으로 삭제되었습니다.", null);
        } catch (Exception e) {
            return ResponseDto.setFailed(e.getMessage());
        }
    }
}