package com.example.back404.teamproject.common;

public class ResponseMessage {
    public static final String SUCCESS = "Success";
    public static final String GET_SUBJECT_LIST_SUCCESS = "과목 전체 목록 조회 성공";
    public static final String GET_SUBJECT_DETAIL_SUCCESS = "과목 상세 정보 조회 성공";
    public static final String UPDATE_SUBJECT_STATUS_SUCCESS = "과목 상태가 성공적으로 변경되었습니다.";
    public static final String APPROVE_SUBJECT_SUCCESS = "강의가 성공적으로 승인 및 생성되었습니다.";
    public static final String DELETE_SUBJECT_SUCCESS = "과목이 성공적으로 삭제되었습니다.";
    public static final String ALREADY_EXISTS_SUBJECT_ID = "이미 존재하는 과목 ID입니다.";
    public static final String CREATE_SUBJECT_SUCCESS = "과목이 성공적으로 등록되었습니다.";
    public static final String UPDATE_SUBJECT_SUCCESS = "과목 정보가 성공적으로 수정되었습니다.";
    public static final String CANNOT_DELETE_SUBJECT_HAS_LECTURE = "해당 과목으로 개설된 강의가 있어 삭제할 수 없습니다.";
    public static final String UPDATE_LECTURE_SUCCESS = "강의 정보가 성공적으로 수정되었습니다.";
    public static final String DELETE_LECTURE_SUCCESS = "강의가 성공적으로 삭제되었습니다.";
    public static final String GET_LECTURE_LIST_SUCCESS = "강의 목록 조회 성공";
    public static final String GET_LECTURE_DETAIL_SUCCESS = "강의 상세 정보 조회 성공";
    public static final String GET_TEACHER_LIST_SUCCESS = "교사 전체 목록 조회 성공";
    public static final String GET_STUDENT_LIST_SUCCESS = "학생 목록 조회 성공";
    public static final String GET_STUDENT_DETAIL_SUCCESS = "학생 상세 정보 조회 성공";
    public static final String NOT_EXISTS_SUBJECT = "존재하지 않는 과목입니다.";
    public static final String NOT_EXISTS_LECTURE = "존재하지 않는 강의입니다.";
    public static final String NOT_EXISTS_TEACHER = "존재하지 않는 교사입니다.";
    public static final String NOT_EXISTS_STUDENT = "존재하지 않는 학생입니다";
    public static final String NOT_EXISTS_SCHOOL = "존재하지 않는 학교 ID입니다.";
    public static final String CANNOT_PROCESS_PENDING_ONLY = "'승인 대기' 상태의 과목만 처리할 수 있습니다.";
}
