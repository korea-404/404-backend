package com.example.back404.teamproject.service.implementations;

import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final JwtProvider jwtProvider;

    @Value("${spring.mail.username}")
    private String senderEmail;

    // 통합 이메일 메일 생성 메서드
    private MimeMessage createCombinedVerificationMail(String email, String token, String code) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(senderEmail);
        message.setRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("학교 관리자 이메일 인증 및 코드 발송");

        String body = """
                <h2>학교 관리자 이메일 인증</h2>
                <p>아래 링크를 클릭하여 이메일 인증을 완료해주세요:</p>
                <a href="http://localhost:8080/api/v1/auth/verify-email?token=%s">이메일 인증 링크</a>
                
                <hr>
                
                <h3>학교 등록용 인증 코드</h3>
                <p>학교 등록을 위해 아래 인증 코드를 입력하세요:</p>
                <h2 style='color:blue;'>%s</h2>
                """.formatted(token, code);

        message.setText(body, "UTF-8", "html");
        return message;
    }

    // 회원가입 시 통합 인증 메일 전송
    @Override
    public void sendVerificationMessage(String email, String code) {
        try {
            String token = jwtProvider.generateEmailValidToken(email);
            MimeMessage message = createCombinedVerificationMail(email, token, code);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("인증 메일 전송 실패: " + e.getMessage());
        }
    }

    // 기존 인증 요청 메서드
    @Override
    public Mono<ResponseEntity<String>> sendSimpleMessage(String email) {
        return Mono.fromCallable(() -> {
            String token = jwtProvider.generateEmailValidToken(email);
            MimeMessage message = createCombinedVerificationMail(email, token, "--------"); // 코드 없음
            javaMailSender.send(message);
            return ResponseEntity.ok("인증 이메일이 전송되었습니다.");
        }).onErrorResume(e -> Mono.just(
                ResponseEntity.badRequest().body("이메일 전송 실패: " + e.getMessage()))
        ).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<ResponseEntity<String>> verifyEmail(String token) {
        return Mono.fromCallable(() -> {
            String email = jwtProvider.getUsernameFromJwt(token);
            return ResponseEntity.ok("이메일 인증 완료: " + email);
        }).onErrorResume(e -> {
            e.printStackTrace();
            return Mono.just(ResponseEntity.badRequest()
                    .body("이메일 인증 실패: " + e.getMessage()));
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
