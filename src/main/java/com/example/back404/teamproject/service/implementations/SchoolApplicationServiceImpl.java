//package com.example.back404.teamproject.service.implementations;
//
//import com.example.back404.teamproject.dto.ResponseDto;
//import com.example.back404.teamproject.dto.auth.SchoolApplicationRequestDto;
//import com.example.back404.teamproject.dto.school.SchoolInfoUpdateRequestDto;
//import com.example.back404.teamproject.entity.School;
//import com.example.back404.teamproject.entity.SchoolApplication;
//import com.example.back404.teamproject.repository.SchoolApplicationRepository;
//import com.example.back404.teamproject.service.SchoolApplicationService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class SchoolApplicationServiceImpl implements SchoolApplicationService {
//
//    private final SchoolApplicationRepository repository;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    @Override
//    public ResponseDto<Long> register(SchoolApplicationRequestDto dto) {
//        SchoolApplication application = SchoolApplication.builder()
//                .schoolName(dto.getSchoolName())
//                .schoolAddress(dto.getSchoolAddress())
//                .schoolContactNumber(dto.getSchoolContactNumber())
//                .schoolAdminName(dto.getSchoolAdminName())
//                .schoolAdminPhoneNumber(dto.getSchoolAdminPhoneNumber())
//                .schoolAdminEmail(dto.getSchoolAdminEmail())
//                .status(SchoolApplication.ApplicationStatus.PENDING)
//                .build();
//
//        repository.save(application);
//        return ResponseDto.setSuccess("학교 신청 등록 완료", application.getId());
//    }
//
//    @Override
//    public ResponseDto<?> getById(Long id) {
//        return repository.findById(id)
//                .map(data -> {
//                    String message = switch (data.getStatus()) {
//                        case APPROVED -> "학교 등록 승인 완료";
//                        case REJECTED -> "학교 등록이 거절되었습니다";
//                        case PENDING -> "학교 등록 신청이 접수되었습니다";
//                    };
//                    return ResponseDto.setSuccess(message, data);
//                })
//                .orElse(ResponseDto.setFailed("해당 신청이 존재하지 않습니다."));
//    }
//
//
//    @Override
//    @Transactional
//    public ResponseDto<String> approve(Long id) {
//        System.out.println("==== approve() 실행됨 ====");
//        Optional<SchoolApplication> optional = repository.findById(id);
//        if (optional.isEmpty()) {
//            return ResponseDto.setFailed("해당 신청이 존재하지 않습니다.");
//        }
//
//        SchoolApplication application = optional.get();
//        System.out.println("기존 상태: " + application.getStatus());
//
//        application.approve();
//        repository.save(application);
//
//        System.out.println("변경 후 상태: " + application.getStatus());
//
//        return ResponseDto.setSuccess("승인 완료", null);
//    }
//
//}