package com.example.back404.teamproject.service;

import com.example.back404.teamproject.dto.common.ResponseDto;
import com.example.back404.teamproject.dto.lecture.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lecture.response.LectureDetailDto;
import com.example.back404.teamproject.dto.lecture.response.LectureListDto;

import java.util.List;

public interface LectureService {
    ResponseDto<LectureListDto> updateLecture(Long lectureId, LectureUpdateRequestDto requestDto);
    ResponseDto<?> deleteLecture(Long lectureId);
    ResponseDto<List<LectureListDto>>getAllLecturesAdmin();
    ResponseDto<List<LectureListDto>> getLectureList(String name);
    ResponseDto<LectureDetailDto> getLectureDetail(Long lectureId);
}