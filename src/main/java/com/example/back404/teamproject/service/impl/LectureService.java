package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.lecture.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lecture.response.LectureDetailResponseDto;
import com.example.back404.teamproject.dto.lecture.response.LectureListDto;
import com.example.back404.teamproject.dto.lecture.response.LectureSimpleResponseDto;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.repository.LectureRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<LectureSimpleResponseDto> getAllLectures() {
        List<Lecture> lectures = lectureRepository.findAllWithSubjectAndTeacher();
        return lectures.stream()
                .map(lecture -> LectureSimpleResponseDto.builder()
                        .id(lecture.getLectureId())
                        .subjectName(lecture.getSubject().getSubjectName())
                        .teacherName(lecture.getTeacher().getName())
                        .dayOfWeek(lecture.getDayOfWeek().toString())
                        .period(lecture.getPeriod())
                        .maxStudents(lecture.getMaxEnrollment())
                        .currentStudents(lecture.getCourseRegistrations().size())
                        .build())
                .collect(Collectors.toList());
    }

    public LectureDetailResponseDto getLectureDetail(Long lectureId) {
        Lecture lecture = lectureRepository.findByIdWithSubjectTeacherClassroom(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));

        return LectureDetailResponseDto.builder()
                .id(lecture.getLectureId())
                .subjectName(lecture.getSubject().getSubjectName())
                .subjectDescription(lecture.getSubject().getDescription())
                .teacherName(lecture.getTeacher().getName())
                .dayOfWeek(lecture.getDayOfWeek().toString())
                .period(lecture.getPeriod())
                .maxStudents(lecture.getMaxEnrollment())
                .currentStudents(lecture.getCourseRegistrations().size())
                .classroom(lecture.getClassroom().getName())
                .build();
    }

    public ResponseDto<LectureListDto> updateLecture(Long lectureId, @Valid LectureUpdateRequestDto requestDto) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의를 찾을 수 없습니다."));

        // 예시: 강의에 강의자/요일/교시/최대 인원 업데이트
        lecture.updateInfo(lecture.getTeacher(), requestDto);
        lectureRepository.save(lecture);

        LectureListDto dto = LectureListDto.builder()
                .lectureId(lecture.getLectureId())
                .subjectName(lecture.getSubject().getSubjectName())
                .teacherName(lecture.getTeacher().getName())
                .dayOfWeek(lecture.getDayOfWeek())
                .period(lecture.getPeriod())
                .allowedGrade(lecture.getAllowedGrade())
                .build();

        return ResponseDto.setSuccess("강의 수정 성공", dto);
    }


    public ResponseDto<?> deleteLecture(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의를 찾을 수 없습니다."));

        lectureRepository.delete(lecture);
        return ResponseDto.setSuccess("강의 삭제 완료", null);
    }

}
