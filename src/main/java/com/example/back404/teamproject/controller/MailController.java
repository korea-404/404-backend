package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.dto.mail.request.SendMailRequestDto;
import com.example.back404.teamproject.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/send-verification")
    public ResponseEntity<?> sendMail(@RequestBody @Valid SendMailRequestDto requestDto) {
        return ResponseEntity.ok(mailService.sendVerificationMail(requestDto));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyMail(@RequestParam String email, @RequestParam String token) {
        return ResponseEntity.ok(mailService.verifyEmailToken(email, token));
    }
}
