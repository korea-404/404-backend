//package com.example.back404.teamproject.service.implementations;
//
//import com.example.back404.teamproject.dto.ResponseDto;
//import com.example.back404.teamproject.dto.auth.SchoolSignUpRequestDto;
//import com.example.back404.teamproject.dto.auth.UserSignInRequestDto;
//import com.example.back404.teamproject.dto.auth.UserSignInResponseDto;
//import com.example.back404.teamproject.dto.school.SchoolInfoUpdateRequestDto;
//import com.example.back404.teamproject.dto.school.SchoolUpdateRequestDto;
//import com.example.back404.teamproject.entity.School;
//import com.example.back404.teamproject.provider.JwtProvider;
//import com.example.back404.teamproject.repository.SchoolRepository;
//import com.example.back404.teamproject.service.MailService;
//import com.example.back404.teamproject.service.SchoolAuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.Optional;
//import java.util.Set;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class SchoolAuthServiceImpl implements SchoolAuthService {
//
//    private final SchoolRepository schoolRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//    private final JwtProvider jwtProvider;
//    private final MailService mailService;
//
//    private String getCurrentUserEmail() {
//        return SecurityContextHolder.getContext().getAuthentication().getName();
//    }
//
//    // 1. 학교 관리자 회원가입
//    @Override
//    @Transactional
//    public ResponseDto<String> register(SchoolSignUpRequestDto dto) {
//
//        if (schoolRepository.existsBySchoolAdminEmail(dto.getSchoolAdminEmail()) ||
//                schoolRepository.existsBySchoolCode(dto.getSchoolCode())) {
//            return ResponseDto.setFailed("이미 사용 중인 이메일 또는 학교 코드입니다.");
//        }
//
//        // 8자리 인증 코드 생성
//        String verificationKey = UUID.randomUUID().toString().substring(0, 8);
//
//        School school = School.builder()
//                .schoolCode(dto.getSchoolCode())
//                .schoolName(dto.getSchoolName())
//                .schoolAddress(dto.getSchoolAddress())
//                .schoolContactNumber(dto.getSchoolContactNumber())
//                .schoolPassword(passwordEncoder.encode(dto.getSchoolPassword()))
//                .schoolAdminName(dto.getSchoolAdminName())
//                .schoolAdminPhoneNumber(dto.getSchoolAdminPhoneNumber())
//                .schoolAdminEmail(dto.getSchoolAdminEmail())
//                .applicationStartedDay(
//                        dto.getApplicationStartedDay() != null ? dto.getApplicationStartedDay() : LocalDate.now())
//                .applicationLimitedDay(
//                        dto.getApplicationLimitedDay() != null ? dto.getApplicationLimitedDay() : LocalDate.now().plusDays(30))
//                .isEmailVerified(false)
//                .schoolCodeVerificationKey(verificationKey)
//                .build();
//
//        schoolRepository.save(school);
//
//        // 이메일 전송
//        mailService.sendVerificationMessage(dto.getSchoolAdminEmail(), verificationKey);
//
//        return ResponseDto.setSuccess("회원가입이 완료되었습니다. 이메일을 확인해 주세요.", null);
//    }
//
//    // 2. 로그인
//    @Override
//    public ResponseDto<UserSignInResponseDto> login(UserSignInRequestDto dto) {
//        Optional<School> optionalSchool = schoolRepository.findBySchoolAdminNameAndIsEmailVerifiedTrue(dto.getSchoolAdminName());
//
//        if (optionalSchool.isEmpty()) {
//            return ResponseDto.setFailed("존재하지 않거나 이메일 인증이 완료되지 않은 관리자입니다.");
//        }
//
//        School school = optionalSchool.get();
//
//        if (!passwordEncoder.matches(dto.getPassword(), school.getSchoolPassword())) {
//            return ResponseDto.setFailed("비밀번호가 일치하지 않습니다.");
//        }
//
//        String token = jwtProvider.generateJwtToken(school.getSchoolAdminEmail(), Set.of("SCHOOL_ADMIN"));
//        UserSignInResponseDto responseDto = new UserSignInResponseDto(token, 3600);
//
//        return ResponseDto.setSuccess("로그인 성공", responseDto);
//    }
//
//    // 3. 이메일 인증 확인
//    @Override
//    @Transactional
//    public ResponseDto<String> verifyEmail(String token) {
//        // 1. 토큰 유효성 검사
//        if (!jwtProvider.isValidToken(token)) {
//            return ResponseDto.setFailed("유효하지 않은 인증 링크입니다.");
//        }
//
//        try {
//            // 2. 토큰에서 이메일 추출
//            String email = jwtProvider.getUsernameFromJwt(token);
//
//            // 3. 이메일로 관리자 계정 조회
//            Optional<School> optionalSchool = schoolRepository.findBySchoolAdminEmail(email);
//            if (optionalSchool.isEmpty()) {
//                return ResponseDto.setFailed("해당 이메일로 등록된 학교 관리자가 없습니다.");
//            }
//
//            // 4. 인증 처리
//            School school = optionalSchool.get();
//
//            if (school.getIsEmailVerified()) {
//                return ResponseDto.setFailed("이미 이메일 인증이 완료된 계정입니다.");
//            }
//
//            school.verifyEmail(); // isEmailVerified = true 처리
//            schoolRepository.save(school);
//
//            return ResponseDto.setSuccess("이메일 인증이 완료되었습니다.", null);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed("서버 오류로 이메일 인증에 실패했습니다: " + e.getMessage());
//        }
//    }
//
//    // 8. 관리자 정보 수정
//    @Override
//    @Transactional
//    public ResponseDto<?> updateMyInfo(SchoolUpdateRequestDto dto) {
//        String email = getCurrentUserEmail();
//        School school = schoolRepository.findBySchoolAdminEmail(email)
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 관리자입니다."));
//
//        school.updateSchoolInfo(
//                dto.getSchoolAddress(),
//                dto.getSchoolContactNumber(),
//                dto.getSchoolAdminName(),
//                dto.getSchoolAdminPhoneNumber(),
//                dto.getSchoolAdminEmail()
//        );
//        schoolRepository.save(school);
//
//        return ResponseDto.setSuccess("내 정보 수정 완료", null);
//    }
//
//    @Override
//    @Transactional
//    public ResponseDto<?> changePassword(String currentPassword, String newPassword) {
//        String email = getCurrentUserEmail(); // 현재 로그인된 사용자 이메일
//        School school = schoolRepository.findBySchoolAdminEmail(email)
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 관리자입니다."));
//
//        if (!passwordEncoder.matches(currentPassword, school.getSchoolPassword())) {
//            return ResponseDto.setFailed("현재 비밀번호가 일치하지 않습니다.");
//        }
//
//        school.changePassword(passwordEncoder.encode(newPassword));
//        schoolRepository.save(school);
//
//        return ResponseDto.setSuccess("비밀번호가 성공적으로 변경되었습니다.", null);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public ResponseDto<?> getMyInfo() {
//        String email = getCurrentUserEmail();
//
//        School school = schoolRepository.findBySchoolAdminEmail(email)
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 관리자입니다."));
//
//        return ResponseDto.setSuccess("내 정보 조회 성공", school);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public ResponseDto<?> getSchoolInfo() {
//        String email = getCurrentUserEmail();
//
//        School school = schoolRepository.findBySchoolAdminEmail(email)
//                .orElseThrow(() -> new RuntimeException("해당 이메일로 등록된 학교가 없습니다."));
//
//        return ResponseDto.setSuccess("학교 정보 조회 성공", school);
//    }
//
//    @Override
//    @Transactional
//    public ResponseDto<?> updateSchoolInfo(SchoolInfoUpdateRequestDto dto) {
//        String email = getCurrentUserEmail();
//
//        School school = schoolRepository.findBySchoolAdminEmail(email)
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 관리자입니다."));
//
//        school.updateSchoolMainInfo(dto.getSchoolName(), dto.getSchoolAddress(), dto.getSchoolContactNumber());
//        schoolRepository.save(school);
//
//        return ResponseDto.setSuccess("학교 정보 수정 완료", null);
//    }
//}