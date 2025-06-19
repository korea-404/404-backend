package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.constants.enums.SchoolStatus;
import com.example.back404.teamproject.dto.ResponseDto;
import com.example.back404.teamproject.dto.auth.SchoolSignUpRequestDto;
import com.example.back404.teamproject.dto.auth.UserSignInRequestDto;
import com.example.back404.teamproject.dto.auth.UserSignInResponseDto;
import com.example.back404.teamproject.dto.school.SchoolInfoUpdateRequestDto;
import com.example.back404.teamproject.dto.school.SchoolUpdateRequestDto;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.repository.SchoolApplicationRepository;
import com.example.back404.teamproject.service.MailService;
import com.example.back404.teamproject.service.SchoolAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SchoolAuthServiceImpl implements SchoolAuthService {

    private final SchoolRepository schoolRepository;
    private final SchoolApplicationRepository schoolApplicationRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final MailService mailService;

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    @Transactional
    public ResponseDto<String> register(SchoolSignUpRequestDto dto) {
        if (!schoolApplicationRepository.existsBySchoolCode(dto.getSchoolCode())) {
            return ResponseDto.setFailed("해당 학교코드는 아직 신청되지 않았습니다.");
        }

        if (schoolRepository.existsBySchoolAdminEmail(dto.getSchoolAdminEmail()) ||
                schoolRepository.existsBySchoolCode(dto.getSchoolCode())) {
            return ResponseDto.setFailed("이미 사용 중인 이메일 또는 학교 코드입니다.");
        }

        School school = School.builder()
                .schoolCode(dto.getSchoolCode())
                .schoolName(dto.getSchoolName())
                .schoolAddress(dto.getSchoolAddress())
                .schoolContactNumber(dto.getSchoolContactNumber())
                .schoolPassword(passwordEncoder.encode(dto.getSchoolPassword()))
                .schoolAdminName(dto.getSchoolAdminName())
                .schoolAdminPhoneNumber(dto.getSchoolAdminPhoneNumber())
                .schoolAdminEmail(dto.getSchoolAdminEmail())
                .applicationStartedDay(
                        dto.getApplicationStartedDay() != null ? dto.getApplicationStartedDay() : LocalDate.now())
                .applicationLimitedDay(
                        dto.getApplicationLimitedDay() != null ? dto.getApplicationLimitedDay() : LocalDate.now().plusDays(30))
                .isEmailVerified(false)
                .status(SchoolStatus.PENDING)
                .build();

        schoolRepository.save(school);

        return ResponseDto.setSuccess("회원가입이 완료되었습니다. 이메일 인증을 진행해주세요.", null);
    }

    @Override
    public ResponseDto<UserSignInResponseDto> login(UserSignInRequestDto dto) {
        Optional<School> optionalSchool = schoolRepository.findBySchoolAdminNameAndIsEmailVerifiedTrue(dto.getSchoolAdminName());

        if (optionalSchool.isEmpty()) {
            return ResponseDto.setFailed("존재하지 않거나 이메일 인증이 완료되지 않은 관리자입니다.");
        }

        School school = optionalSchool.get();

        if (!schoolApplicationRepository.existsBySchoolCode(school.getSchoolCode())) {
            return ResponseDto.setFailed("해당 학교는 신청되지 않은 학교입니다.");
        }

        if (school.getStatus() != SchoolStatus.APPROVED) {
            return ResponseDto.setFailed("아직 승인되지 않은 학교입니다. 관리자 승인을 기다려주세요.");
        }

        if (!passwordEncoder.matches(dto.getPassword(), school.getSchoolPassword())) {
            return ResponseDto.setFailed("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtProvider.generateJwtToken(school.getSchoolAdminEmail(), Set.of("SCHOOL_ADMIN"));
        UserSignInResponseDto responseDto = new UserSignInResponseDto(token, 3600);

        return ResponseDto.setSuccess("로그인 성공", responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<String> verifyEmail(String token) {
        if (!jwtProvider.isValidToken(token)) {
            return ResponseDto.setFailed("유효하지 않은 인증 링크입니다.");
        }

        try {
            String email = jwtProvider.getUsernameFromJwt(token);
            Optional<School> optionalSchool = schoolRepository.findBySchoolAdminEmail(email);
            if (optionalSchool.isEmpty()) {
                return ResponseDto.setFailed("해당 이메일로 등록된 학교 관리자가 없습니다.");
            }

            School school = optionalSchool.get();

            if (school.getIsEmailVerified()) {
                return ResponseDto.setFailed("이미 이메일 인증이 완료된 계정입니다.");
            }

            school.verifyEmail();
            schoolRepository.save(school);

            return ResponseDto.setSuccess("이메일 인증이 완료되었습니다.", null);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("서버 오류로 이메일 인증에 실패했습니다: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> updateMyInfo(SchoolUpdateRequestDto dto) {
        String email = getCurrentUserEmail();
        School school = schoolRepository.findBySchoolAdminEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 관리자입니다."));

        school.updateSchoolInfo(
                dto.getSchoolAddress(),
                dto.getSchoolContactNumber(),
                dto.getSchoolAdminName(),
                dto.getSchoolAdminPhoneNumber(),
                dto.getSchoolAdminEmail()
        );
        schoolRepository.save(school);

        return ResponseDto.setSuccess("내 정보 수정 완료", null);
    }

    @Override
    @Transactional
    public ResponseDto<?> changePassword(String currentPassword, String newPassword) {
        String email = getCurrentUserEmail();
        School school = schoolRepository.findBySchoolAdminEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 관리자입니다."));

        if (!passwordEncoder.matches(currentPassword, school.getSchoolPassword())) {
            return ResponseDto.setFailed("현재 비밀번호가 일치하지 않습니다.");
        }

        school.changePassword(passwordEncoder.encode(newPassword));
        schoolRepository.save(school);

        return ResponseDto.setSuccess("비밀번호가 성공적으로 변경되었습니다.", null);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<?> getMyInfo() {
        String email = getCurrentUserEmail();

        School school = schoolRepository.findBySchoolAdminEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 관리자입니다."));

        return ResponseDto.setSuccess("내 정보 조회 성공", school);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<?> getSchoolInfo() {
        String email = getCurrentUserEmail();

        School school = schoolRepository.findBySchoolAdminEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 이메일로 등록된 학교가 없습니다."));

        return ResponseDto.setSuccess("학교 정보 조회 성공", school);
    }

    @Override
    @Transactional
    public ResponseDto<?> updateSchoolInfo(SchoolInfoUpdateRequestDto dto) {
        String email = getCurrentUserEmail();

        School school = schoolRepository.findBySchoolAdminEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 관리자입니다."));

        // 기본 정보 수정
        school.updateSchoolMainInfo(dto.getSchoolName(), dto.getSchoolAddress(), dto.getSchoolContactNumber());

        // 비밀번호 변경 로직
        if (dto.getNewPassword() != null && !dto.getNewPassword().isBlank()) {
            if (!passwordEncoder.matches(dto.getCurrentPassword(), school.getSchoolPassword())) {
                return ResponseDto.setFailed("현재 비밀번호가 일치하지 않아 비밀번호를 변경할 수 없습니다.");
            }
            school.changePassword(passwordEncoder.encode(dto.getNewPassword()));
        }

        schoolRepository.save(school);

        return ResponseDto.setSuccess("학교 정보 수정 완료", null);
    }
}