package com.example.back404.teamproject.common;

public class ApiMappingPattern {
    // === RESTful API === //
    // 관리자, 교사, 학생 공통 권한 (로그인 시 접근 가능)
    public static final String API_COMMON = "/api/v1/common";

    // 권한, 인증 없는 경로
    public static final String API_AUTH_COMMON = "/api/v1/auth/common";

    // 관리자 단독 권한 - 로그인
    public static final String API_ADMIN = "/api/v1/auth/admin";

    // 교사 단독 권한 - 로그인
    public static final String API_TEACHER = "/api/v1/auth/teacher";

    // 학생 단독 권한 - 로그인
    public static final String API_STUDENT = "/api/v1/auth/student";

    // 과목 관련
    public static final String SUBJECT_API = "/api/v1/subjects";

    // 강의 관련
    public static final String LECTURE_API = "/api/v1/lectures";

    // 로그인 후 (인증된 사용자 API) 일 경우 기능에 맞게 api 작성 (admin, teacher, student 첨부 X)
    // + 관리자-교사 / 교사-학생 공통 권한 포함
    // SecurityConfig 에서 권한 부여 (.hasAnyRole, .hasRole)
}

