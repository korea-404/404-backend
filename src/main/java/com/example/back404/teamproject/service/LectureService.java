package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.constants.ResponseDto;
import com.example.back404.teamproject.dto.lectures.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lectures.response.LectureResponseDto;

public interface LectureService {
    ResponseDto<LectureResponseDto> updateLecture(Long lectureId, LectureUpdateRequestDto requestDto);
    ResponseDto<?> deleteLecture(Long lectureId);
}