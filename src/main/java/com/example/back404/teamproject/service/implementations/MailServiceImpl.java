package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.mail.request.SendMailRequestDto;
import com.example.back404.teamproject.entity.EmailVerification;
import com.example.back404.teamproject.repository.EmailVerificationRepository;
import com.example.back404.teamproject.service.MailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final JavaMailSender mailSender;

    @Override
    @Transactional
    public ResponseDto<?> sendVerificationMail(SendMailRequestDto dto) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(30);

        emailVerificationRepository.save(EmailVerification.builder()
                .email(dto.getEmail())
                .token(token)
                .expiresAt(expiresAt)
                .isVerified(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.getEmail());
        message.setSubject("이메일 인증 요청");
        message.setText("다음 링크를 클릭하여 이메일을 인증하세요:\n" +
                "http://localhost:5173/email/verify?email=" + dto.getEmail() + "&token=" + token);

        mailSender.send(message);
        return ResponseDto.setSuccess("이메일 전송 완료", null);
    }

    @Override
    @Transactional
    public ResponseDto<?> verifyEmailToken(String email, String token) {
        EmailVerification verification = emailVerificationRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 인증 요청이 없습니다."));

        if (verification.isVerified()) {
            return ResponseDto.setFailed("이미 인증된 이메일입니다.");
        }

        if (!verification.getToken().equals(token) || verification.getExpiresAt().isBefore(LocalDateTime.now())) {
            return ResponseDto.setFailed("유효하지 않거나 만료된 토큰입니다.");
        }

        verification.setVerified(true);
        return ResponseDto.setSuccess("이메일 인증 완료", null);
    }
}
