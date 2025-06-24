package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.mail.request.SendMailRequestDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface MailService {
    ResponseDto<?> sendVerificationMail(SendMailRequestDto dto);
    ResponseDto<?> verifyEmailToken(String email, String token);
}

