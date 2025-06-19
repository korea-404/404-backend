package com.example.back404.teamproject.service;

import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.lectures.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lectures.response.LectureDetailDto;
import com.example.back404.teamproject.dto.lectures.response.LectureListDto;
import com.example.back404.teamproject.dto.lectures.response.LectureResponseDto;

import java.util.List;

public interface LectureService {
    ResponseDto<LectureResponseDto> updateLecture(Long lectureId, LectureUpdateRequestDto requestDto);
    ResponseDto<?> deleteLecture(Long lectureId);
    ResponseDto<List<LectureListDto>>getAllLecturesAdmin();
    ResponseDto<List<LectureListDto>> getLectureList(String name);
    ResponseDto<LectureDetailDto> getLectureDetail(Long lectureId);
}