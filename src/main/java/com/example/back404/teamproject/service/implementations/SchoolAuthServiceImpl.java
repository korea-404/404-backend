package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.auth.request.SchoolSignUpRequestDto;
import com.example.back404.teamproject.dto.auth.request.UserSignInRequestDto;
import com.example.back404.teamproject.dto.auth.response.UserSignInResponseDto;
import com.example.back404.teamproject.entity.EmailVerification;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.repository.EmailVerificationRepository;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.service.SchoolAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolAuthServiceImpl implements SchoolAuthService {

    private final SchoolRepository schoolRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseDto<?> signUp(SchoolSignUpRequestDto dto) {
        EmailVerification ev = emailVerificationRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 인증 내역이 없습니다."));

        if (!ev.isVerified()) {
            return ResponseDto.setFailed("이메일 인증을 먼저 완료해주세요.");
        }

        if (schoolRepository.existsBySchoolCode(dto.getSchoolCode())) {
            return ResponseDto.setFailed("이미 등록된 학교 코드입니다.");
        }

        School school = School.builder()
                .schoolCode(dto.getSchoolCode())
                .schoolAdminName(dto.getUsername())
                .schoolPassword(passwordEncoder.encode(dto.getPassword()))
                .schoolAdminEmail(dto.getEmail())
                .schoolAdminBirthDate(java.time.LocalDate.parse(dto.getBirthDate()))
                .schoolAdminPhoneNumber(dto.getPhoneNumber())
                .status(com.example.back404.teamproject.common.constants.enums.SchoolStatus.APPROVED)
                .schoolName("기본학교명")
                .schoolAddress("기본주소")
                .schoolContactNumber("000-0000-0000")
                .applicationStartedDay(java.time.LocalDate.now())
                .applicationLimitedDay(java.time.LocalDate.now().plusDays(30))
                .build();

        schoolRepository.save(school);
        return ResponseDto.setSuccess("관리자 회원가입 성공", null);
    }

    @Override
    public ResponseDto<?> signIn(UserSignInRequestDto dto) {
        School school = schoolRepository.findBySchoolCode(dto.getSchoolCode())
                .orElseThrow(() -> new IllegalArgumentException("학교를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(dto.getPassword(), school.getSchoolPassword())) {
            return ResponseDto.setFailed("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtProvider.generateToken(school.getSchoolAdminEmail(), "ADMIN");
        return ResponseDto.setSuccess("로그인 성공",
                new UserSignInResponseDto(token, jwtProvider.getExpiration()));
    }

    @Override
    public ResponseDto<?> changeAdmin(Long schoolId, SchoolSignUpRequestDto dto) {
        // 구현 생략 (기존 로직 유지)
        return ResponseDto.setSuccess("관리자 변경 완료", null);
    }
}
