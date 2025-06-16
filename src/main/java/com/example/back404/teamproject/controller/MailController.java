package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.dto.auth.SendMailRequestDto;
import com.example.back404.teamproject.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/send-verification")
    public Mono<ResponseEntity<String>> sendVerificationEmail(
            @RequestBody @Valid SendMailRequestDto requestDto
    ) {
        return mailService.sendSimpleMessage(requestDto.getEmail());
    }
}
